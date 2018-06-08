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
package io.reactivex.swt.listener.display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public abstract class DisplayObservable extends Observable<Event> {

	private Display display;
	private int eventType;

	public DisplayObservable(Display display, int eventType) {
		this.display = display;
		this.eventType = eventType;
	}

	@Override
	protected void subscribeActual(Observer<? super Event> observer) {
		if (!Preconditions.checkDisplay(observer, display)) {
			return;
		}
		DisplayListener listener = new DisplayListener(display, eventType, observer);
		observer.onSubscribe(listener);
		display.addListener(SWT.Dispose, e -> listener.dispose());
		addlistener(listener);
	}

	protected abstract void addlistener(Listener listener);

	static final class DisplayListener extends MainThreadDisposable implements Listener {
		private final Display display;
		private final Observer<? super Event> observer;
		private final int eventType;

		DisplayListener(Display display, int eventType, Observer<? super Event> observer) {
			this.display = display;
			this.eventType = eventType;
			this.observer = observer;
		}

		@Override
		protected void onDispose() {
			display.removeListener(eventType, this);
		}

		@Override
		public void handleEvent(Event event) {
			if (!isDisposed()) {
				observer.onNext(event);
			}
		}
	}
}
