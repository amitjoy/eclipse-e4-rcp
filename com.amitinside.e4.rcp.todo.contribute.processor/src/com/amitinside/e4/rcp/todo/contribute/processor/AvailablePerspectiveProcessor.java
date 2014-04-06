package com.amitinside.e4.rcp.todo.contribute.processor;

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;

public class AvailablePerspectiveProcessor {

	@Execute
	public void execute(MApplication app) {
		List<MWindow> list = app.getChildren();
		

	}
}
