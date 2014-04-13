package com.amitinside.e4.rcp.todo.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.core.services.nls.ILocaleChangeService;
import org.eclipse.e4.ui.model.application.MApplication;

public class ChangeLocale {

	@Execute
	public void execute(ILocaleChangeService service, MApplication application,
			Logger logger) {
		IEclipseContext mycontext = application.getContext();
		String locale = (mycontext.get("locale") != null) ? (String) mycontext
				.get("locale") : "";

		if (locale.equals("en")) {
			mycontext.set("locale", "de");
		} else if (locale.equals("de"))
			mycontext.set("locale", "en");
		else
			mycontext.set("locale", "de");
		mycontext.declareModifiable("locale");
		service.changeApplicationLocale(locale);
	}

	@CanExecute
	public boolean canExecute() {
		return true;
	}

}