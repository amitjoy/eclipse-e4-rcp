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
package com.amitinside.e4.rcp.todo.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TodoWizardPage2 extends WizardPage {

	private boolean checked = false;

	/**
	 * Create the wizard.
	 */
	public TodoWizardPage2() {
		super("wizardPage");
		setTitle("Validate");
		setDescription("Check to create the todo item");
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, true);
		container.setLayout(layout);
		Label label = new Label(container, SWT.NONE);
		label.setText("Create the todo");
		Button button = new Button(container, SWT.CHECK);
		button.setText("Check");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checked = ((Button) e.getSource()).getSelection();
				TodoWizard wizard = (TodoWizard) getWizard();
				wizard.finish = checked;
				// the following updates the button
				// it calls anFinish() in the wizard
				wizard.getContainer().updateButtons();
			}
		});
		setControl(container);
	}

	public boolean isChecked() {
		return checked;
	}

}
