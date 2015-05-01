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

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.amitinside.e4.rcp.model.emf.Todo.TodoPackage;
import com.amitinside.e4.rcp.model.emf.Todo.provider.TodoItemProviderAdapterFactory;
import com.amitinside.e4.rcp.model.emf.service.ITodoEMFService;

import de.ralfebert.rcputils.random.RandomData;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public final class EMFTableViewerBindingPart {

	private TableViewerBuilder t;
	private final ITodoEMFService service;
	private final ESelectionService selectionService;
	private final IEclipseContext context;
	private final IWorkbench workbench;
	private final ComposedAdapterFactory composedFactory;

	@Inject
	public EMFTableViewerBindingPart(ESelectionService selectionService,
			IEclipseContext context, IWorkbench workbench,
			ITodoEMFService service) {
		this.service = service;
		this.selectionService = selectionService;
		this.context = context;
		this.workbench = workbench;
		this.composedFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		composingAdapterFactories();
	}

	private void composingAdapterFactories() {
		composedFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		composedFactory
				.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		composedFactory.addAdapterFactory(new TodoItemProviderAdapterFactory());
		composedFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	@PostConstruct
	public void createControl(Composite parent) {
		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		final Section section = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		section.setText("Section 1 for demonstration"); //$NON-NLS-1$
		section.setDescription("This demonstrates the usage of section");

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
		cityComboEditor.setInput(RandomData.CITIES);

		t.createColumn("Cities").makeEditable(cityComboEditor)
				.bindToProperty(TodoPackage.Literals.TODO__NOTE.getName())
				.build();

		toolkit.adapt(t.getTable(), true, true);

		t.getTableViewer().setContentProvider(
				new AdapterFactoryContentProvider(composedFactory));
		t.getTableViewer().setLabelProvider(
				new AdapterFactoryLabelProvider(composedFactory));

		t.getTableViewer().setInput(service.getTodos());

		section.setClient(client);

	}

}