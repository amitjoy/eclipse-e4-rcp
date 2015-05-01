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
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.css.swt.theme.IThemeManager;
import org.eclipse.swt.widgets.Display;

public class ThemeSwitchHandler {
	private static final String DEFAULT_THEME = "com.amitinside.e4.rcp.todo.default";
	private static final String RAINBOW_THEME = "com.amitinside.e4.rcp.todo.rainbow";
	private static final String CUSTOM_JEEEYUL = "com.amitinside.e4.rcp.todo.custom";
	private static final String JEEEYUL_THEME = "net.jeeeyul.eclipse.themes.custom";

	// IThemeEngine can not be injected in case we use Jeeeyul CSS from
	// applicationCSS property (product extension point)
	// As a workaround, we can inject IThemeManager to get IThemeEngine and then
	// we can use it to set new theme dynamically

	@SuppressWarnings("restriction")
	@Execute
	public void switchTheme(IThemeManager manager) {

		final IThemeEngine engine = manager.getEngineForDisplay(Display
				.getCurrent());

		if (engine.getActiveTheme() == null) {
			engine.setTheme(JEEEYUL_THEME, true);
		}
		if (!engine.getActiveTheme().getId().equals(JEEEYUL_THEME)) {
			engine.setTheme(JEEEYUL_THEME, true);
		} else {
			engine.setTheme(CUSTOM_JEEEYUL, true);
		}

	}
}
