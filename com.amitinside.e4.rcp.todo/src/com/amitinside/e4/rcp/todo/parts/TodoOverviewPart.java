/*******************************************************************************
 * Copyright (C) 2015 - Amit Kumar Mondal <admin@amitinside.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.amitinside.e4.rcp.todo.parts;

import static com.amitinside.swt.layout.grid.GridLayoutUtil.applyGridLayout;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.amitinside.e4.rcp.todo.events.MyEventConstants;
import com.amitinside.e4.rcp.todo.model.ITodoService;
import com.amitinside.e4.rcp.todo.model.Todo;

public class TodoOverviewPart {

	private static final String LOCAL_EVENT_FINISH = "finish";
	private Button btnNewButton;
	private Label lblNewLabel;
	private TableViewer viewer;

	private final UISynchronize sync;
	private final ESelectionService service;
	private final IEventBroker broker;
	private final ITodoService model;
	private WritableList writableList;
	protected String searchString = "";

	@Inject
	public TodoOverviewPart(UISynchronize sync, ESelectionService service,
			IEventBroker broker, ITodoService model) {
		this.sync = sync;
		this.service = service;
		this.broker = broker;
		this.model = model;
	}

	@PostConstruct
	public void createControls(Composite parent, final MWindow window,
			EMenuService menuService) {
		applyGridLayout(parent).numColumns(1);

		btnNewButton = new Button(parent, SWT.PUSH);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Job job = new Job("loading") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						final List<Todo> list = model.getTodos();
						broker.post(LOCAL_EVENT_FINISH, list);
						return Status.OK_STATUS;
					}
				};
				job.schedule();
			}
		});
		btnNewButton.setText("Load Data");

		final Text search = new Text(parent, SWT.SEARCH | SWT.CANCEL
				| SWT.ICON_SEARCH);

		// Assuming that GridLayout is used
		search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		search.setMessage("Filter");

		// Filter at every keystroke
		search.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				final Text source = (Text) e.getSource();
				searchString = source.getText();
				// Trigger update in the viewer
				viewer.refresh();
			}
		});

		// SWT.SEARCH | SWT.CANCEL not supported under Windows7
		// This does not work under Windows7
		search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if (e.detail == SWT.CANCEL) {
					final Text text = (Text) e.getSource();
					text.setText("");
					//
				}
			}
		});

		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION);
		final Table table = viewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		final TableViewerColumn colSummary = new TableViewerColumn(viewer,
				SWT.NONE);

		colSummary.getColumn().setWidth(100);
		colSummary.getColumn().setText("Summary");

		colSummary.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, Object value) {
				final Todo todo = (Todo) element;
				todo.setSummary(String.valueOf(value));
				viewer.refresh();
			}

			@Override
			protected Object getValue(Object element) {
				final Todo todo = (Todo) element;
				return todo.getSummary();
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor(viewer.getTable(), SWT.NONE);
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});
		final TableViewerColumn colDescription = new TableViewerColumn(viewer,
				SWT.NONE);

		colDescription.getColumn().setWidth(200);
		colDescription.getColumn().setText("Description");

		// We search in the summary and description field
		viewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				final Todo todo = (Todo) element;
				return todo.getSummary().contains(searchString)
						|| todo.getDescription().contains(searchString)
						|| todo.getNote().contains(searchString);
			}
		});

		final TableViewerColumn colNotes = new TableViewerColumn(viewer,
				SWT.NONE);
		colNotes.getColumn().setWidth(50);
		colNotes.getColumn().setText("Notes");

		colNotes.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, Object value) {
				final Todo todo = (Todo) element;
				todo.setNote(String.valueOf(value));
				viewer.refresh();
			}

			@Override
			protected Object getValue(Object element) {
				final Todo todo = (Todo) element;
				return todo.getNote();
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor(viewer.getTable(), SWT.NONE);
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				final IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				service.setSelection(selection.getFirstElement());
			}
		});
		menuService.registerContextMenu(viewer.getControl(),
				"com.amitinside.e4.rcp.todo.popupmenu.table");
		writableList = new WritableList(model.getTodos(), Todo.class);
		ViewerSupport.bind(
				viewer,
				writableList,
				BeanProperties.values(new String[] { Todo.FIELD_SUMMARY,
						Todo.FIELD_DESCRIPTION, Todo.FIELD_NOTE }));

	}

	@Inject
	@Optional
	private void getNotified(
			@UIEventTopic(MyEventConstants.TOPIC_TODO_ALLTOPICS) Todo todo) {
		if (viewer != null) {
			writableList.clear();
			writableList.addAll(model.getTodos());
		}
	}

	@Focus
	private void setFocus() {
		btnNewButton.setFocus();
	}

}
