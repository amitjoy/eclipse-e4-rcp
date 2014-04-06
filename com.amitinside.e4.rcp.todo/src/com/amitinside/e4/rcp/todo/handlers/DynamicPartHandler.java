package com.amitinside.e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class DynamicPartHandler {
	// Used as reference
	@Execute
	public void execute(MApplication application, EPartService partService,
			EModelService modelService) {
		//
		modelService.find("", application);
		// Create Part based on PartDescriptor
		MPart part = partService
				.createPart("com.amitinside.e4.rcp.todo.partdescriptor.dynamic");
		partService.showPart(part, PartState.ACTIVATE);
	}
}
