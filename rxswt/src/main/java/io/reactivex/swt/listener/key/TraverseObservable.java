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
package io.reactivex.swt.listener.key;

import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Control;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public class TraverseObservable extends Observable<TraverseEvent> {

	private Control control;

	public TraverseObservable(Control control) {
		this.control = control;
	}

	@Override
	protected void subscribeActual(Observer<? super TraverseEvent> observer) {
		if (!Preconditions.checkWidget(observer, control)) {
			return;
		}
		RxTraverseListener listener = new RxTraverseListener(control, observer);
		observer.onSubscribe(listener);
		control.addDisposeListener(e -> listener.dispose());
		control.addTraverseListener(listener);
	}

	static final class RxTraverseListener extends MainThreadDisposable implements TraverseListener {
		private final Control control;
		private final Observer<? super TraverseEvent> observer;

		RxTraverseListener(Control control, Observer<? super TraverseEvent> observer) {
			this.control = control;
			this.observer = observer;
		}

		@Override
		protected void onDispose() {
			control.removeTraverseListener(this);
		}

		@Override
		public void keyTraversed(TraverseEvent e) {
			if (!isDisposed()) {
				observer.onNext(e);
			}
		}
	}
}
