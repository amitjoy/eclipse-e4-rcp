package com.amitinside.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.amitinside.e4.rcp.model.emf.Todo.Todo;
import com.amitinside.e4.rcp.model.emf.Todo.TodoPackage;
import com.amitinside.e4.rcp.model.emf.service.ITodoEMFService;
import com.amitinside.e4.rcp.todo.events.MyEventConstants;

import de.ralfebert.rcputils.random.RandomData;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public final class SampleUIAdapt {

	private final ITodoEMFService service;
	private final ESelectionService selectionService;
	private TableViewerBuilder t;
	private final IObservableList list;
	private final IEclipseContext context;
	private final IWorkbench workbench;

	@Inject
	String name;

	private final IListChangeListener listener = new IListChangeListener() {

		@Override
		public void handleListChange(ListChangeEvent paramListChangeEvent) {
			updateViewer();
		}
	};

	@Inject
	public SampleUIAdapt(ESelectionService selectionService,
			IEclipseContext context, IWorkbench workbench,
			ITodoEMFService service) {
		this.service = service;
		this.selectionService = selectionService;
		this.context = context;
		this.workbench = workbench;
		list = new WritableList();
	}

	@PostConstruct
	public void createControl(Composite parent) {

		System.out.println("NAME=====>" + name);
		System.out.println(context.get("test"));

		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		// Creating the Screen
		final Section section = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		section.setText("Section 1 for demonstration"); //$NON-NLS-1$
		section.setDescription("This demonstrates the usage of section");

		// Composite for storing the data
		final Composite client = toolkit.createComposite(section, SWT.WRAP);

		t = new TableViewerBuilder(client);

		t.createColumn("Summary")
				.bindToProperty(TodoPackage.Literals.TODO__SUMMARY.getName())
				.makeEditable().build();

		t.createColumn("Description")
				.makeEditable()
				.setPixelWidth(300)
				.useAsDefaultSortColumn()
				.bindToProperty(
						TodoPackage.Literals.TODO__DESCRIPTION.getName())
				.build();

		t.getTableViewer().addSelectionChangedListener(
				new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						final IStructuredSelection selection = (IStructuredSelection) t
								.getTableViewer().getSelection();
						selectionService.setSelection(selection
								.getFirstElement());
					}
				});

		final ComboBoxViewerCellEditor cityComboEditor = new ComboBoxViewerCellEditor(
				t.getTable(), SWT.READ_ONLY);
		cityComboEditor.setContentProvider(ArrayContentProvider.getInstance());
		cityComboEditor.setLabelProvider(new LabelProvider());
		cityComboEditor.setInput(RandomData.CITIES);

		t.createColumn("Notes").makeEditable(cityComboEditor)
				.bindToProperty(TodoPackage.Literals.TODO__NOTE.getName())
				.build();

		toolkit.adapt(t.getTable(), true, true);

		t.getTableViewer().setContentProvider(
				ArrayContentProvider.getInstance());

		list.addAll(service.getTodos());
		list.addListChangeListener(listener);

		t.getTableViewer().setInput(list);

		section.setClient(client);

	}

	private void updateViewer() {
		t.getTableViewer().refresh();
	}

	@Inject
	@Optional
	private void getNotified(
			@UIEventTopic(MyEventConstants.TOPIC_TODO_ALLTOPICS) Todo todo) {
		if (t.getTableViewer() != null) {
			list.clear();
			list.addAll(service.getTodos());
		}
	}
}