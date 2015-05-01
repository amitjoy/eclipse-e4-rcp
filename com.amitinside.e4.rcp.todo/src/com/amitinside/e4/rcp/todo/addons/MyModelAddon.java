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
package com.amitinside.e4.rcp.todo.addons;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.model.application.MApplication;

public class MyModelAddon {

	@PostConstruct
	public void init(MApplication application) {
		// Two different ways to inject custom objects

		final IEclipseContext context = application.getContext();
		context.set("test", "hello");

		// Another way
		final IEclipseContext context2 = EclipseContextFactory.create();
		final IEclipseContext context3 = E4Workbench.getServiceContext();

		final String name = "AMIT";
		ContextInjectionFactory.inject(name, context2); // not working for
														// context2
		context3.set(String.class, name);
	}
}
