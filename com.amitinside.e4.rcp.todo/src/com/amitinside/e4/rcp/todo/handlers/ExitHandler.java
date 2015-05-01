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

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class ExitHandler {
	@Execute
	public void execute(EPartService partService,
			IWorkbench workbench, Shell shell) {
		if (!partService.getDirtyParts().isEmpty()) {
			boolean confirm = MessageDialog.openConfirm(shell, "Unsaved",
					"Unsaved data, do you want to save?");
			if (confirm) {
				partService.saveAll(false);
				// Ok we close here directy to avoid 
				// second popup
				workbench.close();
			}
		}

		boolean result = MessageDialog.openConfirm(shell, "Close",
				"Close application?");
		if (result) {
			workbench.close();
		}

	}
}
