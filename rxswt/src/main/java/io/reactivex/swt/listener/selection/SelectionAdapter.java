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

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TreeCursor;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.Widget;

public interface SelectionAdapter {
	void addSelectionListener(SelectionListener selectionListener);

	void removeSelectionListener(SelectionListener selectionListener);

	static SelectionAdapter create(Widget widget) {
		if (widget instanceof Button) {
			return create((Button) widget);
		} else if (widget instanceof CCombo) {
			return create((CCombo) widget);
		} else if (widget instanceof Combo) {
			return create((Combo) widget);
		} else if (widget instanceof CoolItem) {
			return create((CoolItem) widget);
		} else if (widget instanceof CTabFolder) {
			return create((CTabFolder) widget);
		} else if (widget instanceof DateTime) {
			return create((DateTime) widget);
		} else if (widget instanceof Link) {
			return create((Link) widget);
		} else if (widget instanceof List) {
			return create((List) widget);
		} else if (widget instanceof MenuItem) {
			return create((MenuItem) widget);
		} else if (widget instanceof Sash) {
			return create((Sash) widget);
		} else if (widget instanceof Scale) {
			return create((Scale) widget);
		} else if (widget instanceof Slider) {
			return create((Slider) widget);
		} else if (widget instanceof Spinner) {
			return create((Spinner) widget);
		} else if (widget instanceof StyledText) {
			return create((StyledText) widget);
		} else if (widget instanceof TabFolder) {
			return create((TabFolder) widget);
		} else if (widget instanceof Table) {
			return create((Table) widget);
		} else if (widget instanceof TableColumn) {
			return create((TableColumn) widget);
		} else if (widget instanceof TableCursor) {
			return create((TableCursor) widget);
		} else if (widget instanceof Text) {
			return create((Text) widget);
		} else if (widget instanceof ToolItem) {
			return create((ToolItem) widget);
		} else if (widget instanceof ToolTip) {
			return create((ToolTip) widget);
		} else if (widget instanceof TrayItem) {
			return create((TrayItem) widget);
		} else if (widget instanceof Tree) {
			return create((Tree) widget);
		} else if (widget instanceof TreeColumn) {
			return create((TreeColumn) widget);
		} else if (widget instanceof TreeCursor) {
			return create((TreeCursor) widget);
		}

		throw new IllegalArgumentException("The given widget (" + widget.getClass().getName() + ") is not supported.");
	}

	static SelectionAdapter create(Button button) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				button.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				button.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Combo combo) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				combo.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				combo.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(CoolItem coolItem) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				coolItem.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				coolItem.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(DateTime dateTime) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				dateTime.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				dateTime.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Link link) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				link.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				link.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(List list) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				list.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				list.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(MenuItem menuItem) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				menuItem.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				menuItem.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Sash sash) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				sash.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				sash.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Scale scale) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				scale.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				scale.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Slider slider) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				slider.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				slider.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Spinner spinner) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				spinner.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				spinner.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(TabFolder tabFolder) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				tabFolder.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				tabFolder.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Table table) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				table.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				table.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(TableColumn tableColumn) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				tableColumn.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				tableColumn.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Text text) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				text.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				text.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(ToolItem toolItem) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				toolItem.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				toolItem.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(ToolTip tooltip) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				tooltip.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				tooltip.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(TrayItem trayItem) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				trayItem.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				trayItem.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(Tree tree) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				tree.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				tree.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(TreeColumn treeColumn) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				treeColumn.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				treeColumn.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(CCombo cCombo) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				cCombo.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				cCombo.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(CTabFolder cTabFolder) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				cTabFolder.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				cTabFolder.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(StyledText styledText) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				styledText.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				styledText.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(TableCursor tableCursor) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				tableCursor.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				tableCursor.addSelectionListener(selectionListener);
			}
		};
	}

	static SelectionAdapter create(TreeCursor treeCursor) {
		return new SelectionAdapter() {
			@Override
			public void removeSelectionListener(SelectionListener selectionListener) {
				treeCursor.removeSelectionListener(selectionListener);
			}

			@Override
			public void addSelectionListener(SelectionListener selectionListener) {
				treeCursor.addSelectionListener(selectionListener);
			}
		};
	}
}
