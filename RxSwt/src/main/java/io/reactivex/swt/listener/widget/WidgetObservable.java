/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.reactivex.swt.listener.widget;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public class WidgetObservable extends Observable<Event> {

	private Widget widget;
	private int eventType;

	public WidgetObservable(Widget widget, int eventType) {
		this.widget = widget;
		this.eventType = eventType;
	}

	@Override
	protected void subscribeActual(Observer<? super Event> observer) {
		if (!Preconditions.checkWidget(observer, widget)) {
			return;
		}
		WidgetListener listener = new WidgetListener(widget, eventType, observer);
		observer.onSubscribe(listener);
		widget.addDisposeListener(e -> listener.dispose());
		widget.addListener(eventType, listener);
	}

	static final class WidgetListener extends MainThreadDisposable implements Listener {
		private final Widget widget;
		private final Observer<? super Event> observer;
		private final int eventType;

		WidgetListener(Widget widget, int eventType, Observer<? super Event> observer) {
			this.widget = widget;
			this.eventType = eventType;
			this.observer = observer;
		}

		@Override
		protected void onDispose() {
			widget.removeListener(eventType, this);
		}

		@Override
		public void handleEvent(Event event) {
			if (!isDisposed()) {
				observer.onNext(event);
			}
		}
	}
}
