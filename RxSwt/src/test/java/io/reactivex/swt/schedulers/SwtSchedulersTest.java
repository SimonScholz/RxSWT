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
package io.reactivex.swt.schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.swt.testutil.EmptyScheduler;
import io.reactivex.swt.util.RxSwtPlugins;

public final class SwtSchedulersTest {

	@Before
	@After
	public void setUpAndTearDown() {
		RxSwtPlugins.reset();
	}

	@Test
	public void testMainThreadCallsThroughToHookTest() {
		final AtomicInteger called = new AtomicInteger();
		final Scheduler newScheduler = new EmptyScheduler();
		RxSwtPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
			@Override
			public Scheduler apply(Scheduler scheduler) {
				called.getAndIncrement();
				return newScheduler;
			}
		});

		assertSame(newScheduler, SwtSchedulers.defaultDisplayThread());
		assertEquals(1, called.get());

		assertSame(newScheduler, SwtSchedulers.defaultDisplayThread());
		assertEquals(2, called.get());
	}

	@Test
	public void fromNullThrows() {
		try {
			SwtSchedulers.from(null);
		} catch (NullPointerException e) {
			fail("Calling SwtSchedulers.from(null) should not result in an NPE, but use the default display.");
		}
	}

	@Test
	public void fromReturnsUsableScheduler() {
//		assertNotNull(AndroidSchedulers.from(Looper.getMainLooper()));
	}
}
