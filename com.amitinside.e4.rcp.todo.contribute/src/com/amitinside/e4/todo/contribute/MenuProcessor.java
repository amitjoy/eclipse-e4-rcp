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
package com.amitinside.e4.todo.contribute;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;

import com.amitinside.e4.todo.contribute.handler.ExitHandlerWithCheck;

public class MenuProcessor {

	// I get this via the parameter
	// of the process definition
	@Inject
	@Named("org.eclipse.ui.file.menu")
	private MMenu menu;

	@Execute
	public void execute() {
		// Remove the old exit menu entry
		if (menu != null && menu.getChildren() != null) {
			List<MMenuElement> list = new ArrayList<MMenuElement>();
			for (MMenuElement element : menu.getChildren()) {
				// Separaters have no label hence we
				// need to check for null
				if (element.getLabel() != null) {
					if (element.getLabel().contains("Exit")) {
						list.add(element);
					}
				}
			}
			menu.getChildren().removeAll(list);

			// Now add a new menu entry
			MDirectMenuItem menuItem = MMenuFactory.INSTANCE
					.createDirectMenuItem();
			menuItem.setLabel("Another Exit");
			menuItem.setContributionURI("bundleclass://"
					+ "com.amitinside.e4.rcp.todo.contribute/"
					+ ExitHandlerWithCheck.class.getName());
			menu.getChildren().add(menuItem);
		}

	}
}
