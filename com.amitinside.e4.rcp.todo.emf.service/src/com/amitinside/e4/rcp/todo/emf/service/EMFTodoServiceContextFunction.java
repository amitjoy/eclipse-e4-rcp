package com.amitinside.e4.rcp.todo.emf.service;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;

import com.amitinside.e4.rcp.model.emf.service.ITodoEMFService;

public class EMFTodoServiceContextFunction extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		final ITodoEMFService todoEMFService = ContextInjectionFactory.make(
				EMFTodoServiceImpl.class, context);
		final MApplication app = context.get(MApplication.class);
		final IEclipseContext appCtx = app.getContext();
		appCtx.set(ITodoEMFService.class, todoEMFService);

		return todoEMFService;
	}
}
