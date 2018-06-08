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

import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.swt.listener.SwtObservables;

public class SelectionObservableTest {

	private Shell shell;

	@Before
	public void setUp() {
		shell = new Shell();
	}

	@After
	public void tearDown() {
		shell.dispose();
		shell = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testWidgetSelectedThrowsIllegalArgumentException() {
		SwtObservables.widgetSelected(shell);
	}
}
