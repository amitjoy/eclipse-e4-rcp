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