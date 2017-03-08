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
package io.reactivex.swt.listener.paint;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Control;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public class PaintObservable extends Observable<PaintEvent> {

	private Control control;

	public PaintObservable(Control control) {
		this.control = control;
	}

	@Override
	protected void subscribeActual(Observer<? super PaintEvent> observer) {
		if (!Preconditions.checkWidget(observer, control)) {
			return;
		}
		RxPaintListener listener = new RxPaintListener(control, observer);
		observer.onSubscribe(listener);
		control.addDisposeListener(e -> listener.dispose());
		control.addPaintListener(listener);
	}

	static final class RxPaintListener extends MainThreadDisposable implements PaintListener {
		private final Control control;
		private final Observer<? super PaintEvent> observer;

		RxPaintListener(Control control, Observer<? super PaintEvent> observer) {
			this.control = control;
			this.observer = observer;
		}

		@Override
		protected void onDispose() {
			control.removePaintListener(this);
		}

		@Override
		public void paintControl(PaintEvent e) {
			if (!isDisposed()) {
				observer.onNext(e);
			}
		}
	}
}
