package io.reactivex.swt.testutil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.TestObserver;

public class TestObserverUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TestObserver<?> getEmptySubscribedTestObserver() {
		Observable<?> empty = Observable.empty();
		TestObserver<?> testObserver = TestObserver.create();
		empty.subscribe((Observer) testObserver);
		return testObserver;
	}

	private TestObserverUtil() {
	}
}
