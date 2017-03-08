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
package io.reactivex.swt.listener.control;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Control;

import io.reactivex.Observer;

public class ControlMovedObservable extends ControlObservable {

	public ControlMovedObservable(Control control) {
		super(control);
	}

	@Override
	protected AbstractControlListener getControlListener(Control control, Observer<? super ControlEvent> observer) {
		return new AbstractControlListener(control) {
			@Override
			public void controlMoved(ControlEvent e) {
				if (!isDisposed()) {
					observer.onNext(e);
				}
			}
		};
	}

}
