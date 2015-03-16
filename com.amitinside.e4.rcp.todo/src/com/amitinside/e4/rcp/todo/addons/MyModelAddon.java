package com.amitinside.e4.rcp.todo.addons;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;

public class MyModelAddon {

	@PostConstruct
	public void init(MApplication application) {
		final IEclipseContext context = application.getContext();
		context.set("test", "hello");
	}
}
