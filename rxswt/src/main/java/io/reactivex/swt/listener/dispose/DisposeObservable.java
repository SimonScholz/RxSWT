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
package io.reactivex.swt.listener.dispose;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Widget;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public class DisposeObservable extends Observable<DisposeEvent> {

	private Widget widget;

	public DisposeObservable(Widget widget) {
		this.widget = widget;
	}

	@Override
	protected void subscribeActual(Observer<? super DisposeEvent> observer) {
		if (!Preconditions.checkWidget(observer, widget)) {
			return;
		}
		RxDisposeListener listener = new RxDisposeListener(widget, observer);
		observer.onSubscribe(listener);
		widget.addDisposeListener(e -> listener.dispose());
		widget.addDisposeListener(listener);
	}

	static final class RxDisposeListener extends MainThreadDisposable implements DisposeListener {
		private final Widget widget;
		private final Observer<? super DisposeEvent> observer;

		RxDisposeListener(Widget widget, Observer<? super DisposeEvent> observer) {
			this.widget = widget;
			this.observer = observer;
		}

		@Override
		protected void onDispose() {
			widget.removeDisposeListener(this);
		}

		@Override
		public void widgetDisposed(DisposeEvent e) {
			if (!isDisposed()) {
				observer.onNext(e);
				observer.onComplete();
			}
		}
	}
}
