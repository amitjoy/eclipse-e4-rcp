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

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.amitinside.swt.layout.grid.GridDataUtil;
import com.amitinside.swt.layout.grid.GridLayoutUtil;

public class FormsUIPart {
	private TableViewer viewer;

	@Inject
	public FormsUIPart() {

	}

	@PostConstruct
	public void createControls(Composite parent) {

		final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		final ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setText("Eclipse Forms API Example");
		final TableWrapLayout layout = new TableWrapLayout();
		form.getBody().setLayout(layout);
		toolkit.createLabel(form.getBody(), "Snippet2");

		final ExpandableComposite composite = toolkit
				.createExpandableComposite(form.getBody(),
						ExpandableComposite.TREE_NODE
								| ExpandableComposite.CLIENT_INDENT);
		composite.setText("Expand Me");
		final String text = "Lots of text, Lots of text,";
		final Label label = toolkit.createLabel(composite, text, SWT.WRAP);
		composite.setClient(label);
		final TableWrapData td = new TableWrapData();
		td.colspan = 1;
		composite.setLayoutData(td);
		composite.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		// Creating the Screen
		final Section section = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		section.setText("Section 1 for demonstration"); //$NON-NLS-1$
		section.setDescription("This demonstrates the usage of section");

		// Composite for storing the data
		final Composite client = toolkit.createComposite(section, SWT.WRAP);

		GridLayoutUtil.applyGridLayout(client).numColumns(2).marginHeight(2)
				.marginWidth(2);

		final Table t = toolkit.createTable(client, SWT.NONE);

		GridDataUtil.applyGridData(t).withFill().heightHint(20).widthHint(100);

		toolkit.paintBordersFor(client);

		final Button b = toolkit.createButton(client, "Do something", SWT.PUSH); //$NON-NLS-1$

		GridDataUtil.applyGridData(b).verticalAlignment(
				GridData.VERTICAL_ALIGN_BEGINNING);

		section.setClient(client);

		viewer = new TableViewer(t);
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);

		viewerColumn.getColumn().setWidth(100);
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return element.toString();
			};

		});

	}
}
