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
package io.reactivex.eclipse;

import io.reactivex.Scheduler;
import io.reactivex.eclipse.scheduler.EclipseScheduler;
import io.reactivex.eclipse.util.RxEclipsePlugins;

public final class EclipseSchedulers {

	/**
	 * A {@link Scheduler} which executes actions on a {@code Display}.
	 */
	public static Scheduler create(String jobName) {
		return RxEclipsePlugins.onMainThreadScheduler(new EclipseScheduler(jobName));
	}

	private EclipseSchedulers() {
		throw new AssertionError("No instances.");
	}
}
