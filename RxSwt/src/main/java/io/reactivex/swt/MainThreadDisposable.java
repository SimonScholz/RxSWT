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
package io.reactivex.swt;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.widgets.Display;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.swt.schedulers.SwtSchedulers;

/**
 * A {@linkplain Disposable disposable} which ensures its
 * {@linkplain #onDispose() dispose action} is executed on the main thread. When
 * unsubscription occurs on a different thread than the main thread, the action
 * is posted to run on the main thread as soon as possible.
 * <p>
 * Instances of this class are useful in creating observables which interact
 * with APIs that can only be used on the main thread, such as UI objects.
 * </p>
 */
public abstract class MainThreadDisposable implements Disposable {

	private final AtomicBoolean unsubscribed = new AtomicBoolean();

	private Display display;

	/**
	 * When using this constructor the default display will be used to schedule
	 * the call of the onDispose method.
	 */
	public MainThreadDisposable() {
	}

	/**
	 * When using this constructor the given display will be used to schedule
	 * the call of the onDispose method.
	 * 
	 * @param display
	 *            {@link Display} to be used to schedule the call of the
	 *            onDispose method
	 */
	public MainThreadDisposable(Display display) {
		this.display = display;
	}

	@Override
	public final boolean isDisposed() {
		return unsubscribed.get();
	}

	@Override
	public final void dispose() {
		if (unsubscribed.compareAndSet(false, true)) {
			if (Display.getCurrent() != null) {
				onDispose();
			} else {
				getScheduler().scheduleDirect(new Runnable() {
					@Override
					public void run() {
						onDispose();
					}
				});
			}
		}
	}

	protected Scheduler getScheduler() {
		if (display != null) {
			return SwtSchedulers.from(display);
		}
		return SwtSchedulers.defaultDisplayThread();
	}

	protected abstract void onDispose();

}
