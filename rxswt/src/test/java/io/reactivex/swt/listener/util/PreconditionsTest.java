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

import static io.reactivex.swt.testutil.TestObserverUtil.getEmptySubscribedTestObserver;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;

public class PreconditionsTest {

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

	@Test
	public void testCheckWidgetCalledFromMainThread() {
		TestObserver<?> testObserver = TestObserver.create();

		boolean checkWidget = Preconditions.checkWidget(testObserver, shell);

		assertThat(checkWidget, is(true));

		testObserver.assertNoErrors();
	}

	@Test
	public void testCheckWidgetCalledFromNonMainThread() throws InterruptedException, TimeoutException {
		TestObserver<?> testObserver = getEmptySubscribedTestObserver();

		AtomicBoolean atomic = new AtomicBoolean(false);

		Thread thread = new Thread(() -> {
			boolean checkWidget = Preconditions.checkWidget(testObserver, shell);

			assertThat(checkWidget, is(false));

			testObserver.assertError(IllegalStateException.class);
			testObserver.assertErrorMessage(
					"Expected to be called on the main thread but was " + Thread.currentThread().getName());
			atomic.set(true);
		});
		thread.start();

		await().untilTrue(atomic);
	}

	@Test
	public void testCheckWidgetWithDisposedWidget() {
		TestObserver<?> testObserver = getEmptySubscribedTestObserver();

		shell.dispose();
		boolean checkWidget = Preconditions.checkWidget(testObserver, shell);

		assertThat(checkWidget, is(false));

		testObserver.assertError(IllegalStateException.class);
		testObserver.assertErrorMessage("The given widget is diposed");
	}

	@Test
	public void testCheckWidgetWithNullWidget() {
		TestObserver<?> testObserver = getEmptySubscribedTestObserver();

		boolean checkWidget = Preconditions.checkWidget(testObserver, null);

		assertThat(checkWidget, is(false));

		testObserver.assertError(NullPointerException.class);
		testObserver.assertErrorMessage("The given widget was null");
	}
}
