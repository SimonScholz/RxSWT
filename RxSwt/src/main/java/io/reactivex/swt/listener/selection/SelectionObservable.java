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
package io.reactivex.swt.listener.selection;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Widget;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.swt.MainThreadDisposable;
import io.reactivex.swt.listener.util.Preconditions;

public class SelectionObservable extends Observable<SelectionEvent> {

	private Widget widget;
	private SelectionAdapter selectionAdapter;

	public SelectionObservable(Widget widget) {
		this.widget = widget;
		selectionAdapter = SelectionAdapter.create(widget);
	}

	@Override
	protected void subscribeActual(Observer<? super SelectionEvent> observer) {
		if (!Preconditions.checkWidget(observer, widget)) {
			return;
		}

		RxSelectionListener listener = new RxSelectionListener(selectionAdapter, observer);
		observer.onSubscribe(listener);
		widget.addDisposeListener(e -> listener.dispose());
		selectionAdapter.addSelectionListener(listener);
	}

	static final class RxSelectionListener extends MainThreadDisposable implements SelectionListener {
		private final SelectionAdapter selectionAdapter;
		private final Observer<? super SelectionEvent> observer;

		RxSelectionListener(SelectionAdapter selectionAdapter, Observer<? super SelectionEvent> observer) {
			this.selectionAdapter = selectionAdapter;
			this.observer = observer;
		}

		@Override
		protected void onDispose() {
			selectionAdapter.removeSelectionListener(this);
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (!isDisposed()) {
				observer.onNext(e);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
}
