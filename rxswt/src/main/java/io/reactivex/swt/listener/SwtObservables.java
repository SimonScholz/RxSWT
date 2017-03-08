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
package io.reactivex.swt.listener;

import javax.swing.table.TableColumn;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TreeCursor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.Widget;

import io.reactivex.Observable;
import io.reactivex.swt.listener.dispose.DisposeObservable;
import io.reactivex.swt.listener.mouse.MouseDoubleClickObservable;
import io.reactivex.swt.listener.mouse.MouseDownObservable;
import io.reactivex.swt.listener.mouse.MouseEnterObservable;
import io.reactivex.swt.listener.mouse.MouseExitObservable;
import io.reactivex.swt.listener.mouse.MouseUpObservable;
import io.reactivex.swt.listener.mouse.MouseWheelObservable;
import io.reactivex.swt.listener.selection.SelectionObservable;
import io.reactivex.swt.listener.widget.WidgetObservable;

public class SwtObservables {

	public static Observable<DisposeEvent> widgetDisposed(Widget widget) {
		checkNotNull(widget, "widget == null");
		return new DisposeObservable(widget);
	}

	public static Observable<Event> from(Widget widget, int eventType) {
		checkNotNull(widget, "widget == null");
		return new WidgetObservable(widget, eventType);
	}

	public static Observable<MouseEvent> mouseEnter(Control control) {
		checkNotNull(control, "control == null");
		return new MouseEnterObservable(control);
	}

	public static Observable<MouseEvent> mouseExit(Control control) {
		checkNotNull(control, "control == null");
		return new MouseExitObservable(control);
	}

	public static Observable<MouseEvent> mouseHover(Control control) {
		checkNotNull(control, "control == null");
		return new MouseExitObservable(control);
	}

	public static Observable<MouseEvent> mouseUp(Control control) {
		checkNotNull(control, "control == null");
		return new MouseUpObservable(control);
	}

	public static Observable<MouseEvent> mouseDown(Control control) {
		checkNotNull(control, "control == null");
		return new MouseDownObservable(control);
	}

	public static Observable<MouseEvent> mouseDoubleClick(Control control) {
		checkNotNull(control, "control == null");
		return new MouseDoubleClickObservable(control);
	}

	public static Observable<MouseEvent> mouseScrolled(Control control) {
		checkNotNull(control, "control == null");
		return new MouseWheelObservable(control);
	}

	/**
	 * Create an {@link Observable} for listening to a widget's selection.
	 * 
	 * <p>
	 * Supported widgets are:<br/>
	 * <br/>
	 * {@link CCombo}<br/>
	 * {@link CTabFolder}<br/>
	 * {@link StyledText}<br/>
	 * {@link TableCursor}<br/>
	 * {@link TreeCursor}<br/>
	 * {@link Button}<br/>
	 * {@link Combo}<br/>
	 * {@link CoolItem}<br/>
	 * {@link DateTime}<br/>
	 * {@link Link}<br/>
	 * {@link List}<br/>
	 * {@link MenuItem}<br/>
	 * {@link Sash}<br/>
	 * {@link Scale}<br/>
	 * {@link Slider}<br/>
	 * {@link Spinner}<br/>
	 * {@link TabFolder}<br/>
	 * {@link Table}<br/>
	 * {@link TableColumn}<br/>
	 * {@link Text}<br/>
	 * {@link ToolItem}<br/>
	 * {@link ToolTip}<br/>
	 * {@link TrayItem}<br/>
	 * {@link Tree}<br/>
	 * {@link TreeColumn}<br/>
	 * </p>
	 * 
	 * @param widget
	 *            {@link Widget}, which supports selection listening
	 * @return an {@link Observable}, which emits {@link SelectionEvent}
	 *         objects.
	 */
	public static Observable<SelectionEvent> widgetSelected(Widget widget) {
		checkNotNull(widget, "widget == null");
		return new SelectionObservable(widget);
	}

	private SwtObservables() {
	}

	private static void checkNotNull(Object value, String message) {
		if (value == null) {
			throw new NullPointerException(message);
		}
	}
}
