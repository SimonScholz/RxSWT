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

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.swt.schedulers.SwtSchedulers;

public class App {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private static Text text;

	public static void main(String[] args) {

		GitHubApi gitHubApi = ApiService.getGitHubApi();

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(600, 600);

		shell.setLayout(new GridLayout(2, false));

		Button button = new Button(shell, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setText("Load data");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// Get an observable to subscribe to
				Single<List<Contributor>> contributorsObservable = gitHubApi.contributors("eclipse",
						"eclipse.platform.swt");

				// Do query for contributors on another thread and sync with the
				// SWT main thread.
				// Comment out the next lines and see the UI freezing by trying
				// to click the check button.
				contributorsObservable = contributorsObservable.subscribeOn(Schedulers.io())
						.observeOn(SwtSchedulers.currentDisplayThread());

				// Show the contributors in the text field by subscribing or on
				// error do system print line
				contributorsObservable.subscribe(contributors -> {
					StringBuilder sb = new StringBuilder();
					for (Contributor contributor : contributors) {
						sb.append(contributor.getLogin());
						sb.append(" : ");
						sb.append(contributor.getContributions());
						sb.append(LINE_SEPARATOR);
					}
					text.setText(sb.toString());
				}, System.out::println);
			}
		});

		// This button is just intended to prove UI freezes, if no
		// schedulers are applied
		Button responsiveButton = new Button(shell, SWT.CHECK);
		responsiveButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		responsiveButton.setText("I won't be responsive when querying on the main thread");

		text = new Text(shell, SWT.MULTI | SWT.LEAD | SWT.BORDER | SWT.READ_ONLY);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		text.setText("Press load data");

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
