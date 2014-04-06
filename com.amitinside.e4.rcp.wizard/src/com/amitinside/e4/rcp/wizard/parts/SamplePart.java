package com.amitinside.e4.rcp.wizard.parts;

import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class SamplePart {

	private Label label;
	private TableViewer tableViewer;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout());

		label = new Label(parent, SWT.NONE);
		label.setText("Sample table");

		tableViewer = new TableViewer(parent);
		tableViewer.add("Sample item 1");
		tableViewer.add("Sample item 2");
		tableViewer.add("Sample item 3");
		tableViewer.add("Sample item 4");
		tableViewer.add("Sample item 5");
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}
}
