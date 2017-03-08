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
package io.reactivex.swt.listener.mouse;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Control;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public abstract class MouseObservable extends Observable<MouseEvent> {

	private Control control;

	public MouseObservable(Control control) {
		this.control = control;
	}

	@Override
	protected void subscribeActual(Observer<? super MouseEvent> observer) {
		if (!Preconditions.checkWidget(observer, control)) {
			return;
		}
		AbstractMouseListener listener = getMouseListener(control, observer);
		observer.onSubscribe(listener);
		control.addDisposeListener(e -> listener.dispose());
		control.addMouseListener(listener);

	}

	protected abstract AbstractMouseListener getMouseListener(Control control, Observer<? super MouseEvent> observer);

	static abstract class AbstractMouseListener extends MainThreadDisposable implements MouseListener {
		private final Control control;

		AbstractMouseListener(Control control) {
			this.control = control;
		}

		@Override
		protected void onDispose() {
			control.removeMouseListener(this);
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}

		@Override
		public void mouseDown(MouseEvent e) {
		}

		@Override
		public void mouseUp(MouseEvent e) {
		}
	}
}
