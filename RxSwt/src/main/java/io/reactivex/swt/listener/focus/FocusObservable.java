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
package io.reactivex.swt.listener.focus;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Control;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public abstract class FocusObservable extends Observable<FocusEvent> {

	private Control control;

	public FocusObservable(Control control) {
		this.control = control;
	}

	@Override
	protected void subscribeActual(Observer<? super FocusEvent> observer) {
		if (!Preconditions.checkWidget(observer, control)) {
			return;
		}
		AbstractFocusListener listener = getFocusListener(control, observer);
		observer.onSubscribe(listener);
		control.addDisposeListener(e -> listener.dispose());
		control.addFocusListener(listener);

	}

	protected abstract AbstractFocusListener getFocusListener(Control control,
			Observer<? super FocusEvent> observer);

	static abstract class AbstractFocusListener extends MainThreadDisposable implements FocusListener {
		private final Control control;

		AbstractFocusListener(Control control) {
			this.control = control;
		}

		@Override
		protected void onDispose() {
			control.removeFocusListener(this);
		}

		@Override
		public void focusGained(FocusEvent e) {
		}

		@Override
		public void focusLost(FocusEvent e) {
		}
	}
}
