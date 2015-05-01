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
package com.amitinside.e4.rcp.todo.handlers;

import java.util.Date;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.amitinside.e4.rcp.todo.model.ITodoService;
import com.amitinside.e4.rcp.todo.model.Todo;
import com.amitinside.e4.rcp.todo.wizards.TodoWizard;

public class NewTodoHandler {
	@Execute
	public void execute(Shell shell, ITodoService model) {
		Todo todo = new Todo();
		todo.setDueDate(new Date());
		WizardDialog dialog = new WizardDialog(shell, new TodoWizard(todo));
		if (dialog.open()== WizardDialog.OK) {
			model.saveTodo(todo);
		}
	}
}
