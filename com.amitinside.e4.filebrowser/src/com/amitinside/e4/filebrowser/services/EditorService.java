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
package com.amitinside.e4.filebrowser.services;

import java.net.URI;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class EditorService {

	public void createPart(MApplication application, EPartService partService,
			EModelService modelService, URI uri) {
		final MUIElement find = modelService.find("editorarea", application);
		final MPart part = partService
				.createPart("com.amitinside.e4.rcp.todo.partdescriptor.fileeditor");
		part.setLabel("New Dynamic Part");

		// If multiple parts of this type is allowed
		// in the application model,
		// then the provided part will be shown
		// and returned
		partService.showPart(part, PartState.ACTIVATE);
	}
}
