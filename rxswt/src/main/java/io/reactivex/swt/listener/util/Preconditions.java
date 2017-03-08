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
package io.reactivex.swt.listener.util;

import org.eclipse.swt.widgets.Widget;

import io.reactivex.Observer;

public class Preconditions {
	public static boolean checkWidget(Observer<?> observer, Widget widget) {
		if (!widget.isDisposed() && !(Thread.currentThread().equals(widget.getDisplay().getThread()))) {
			observer.onError(new IllegalStateException(
					"Expected to be called on the main thread but was " + Thread.currentThread().getName()));
			return false;
		}
		return true;
	}
}
