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

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Control;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public abstract class KeyObservable extends Observable<KeyEvent> {

	private Control control;

	public KeyObservable(Control control) {
		this.control = control;
	}

	@Override
	protected void subscribeActual(Observer<? super KeyEvent> observer) {
		if (!Preconditions.checkWidget(observer, control)) {
			return;
		}
		AbstractKeyListener listener = getMouseListener(control, observer);
		observer.onSubscribe(listener);
		control.addDisposeListener(e -> listener.dispose());
		control.addKeyListener(listener);

	}

	protected abstract AbstractKeyListener getMouseListener(Control control, Observer<? super KeyEvent> observer);

	static abstract class AbstractKeyListener extends MainThreadDisposable implements KeyListener {
		private final Control control;

		AbstractKeyListener(Control control) {
			this.control = control;
		}

		@Override
		protected void onDispose() {
			control.removeKeyListener(this);
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
