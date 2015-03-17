package com.amitinside.e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.css.swt.theme.IThemeManager;
import org.eclipse.swt.widgets.Display;

public class ThemeSwitchHandler {
	private static final String DEFAULT_THEME = "com.amitinside.e4.rcp.todo.default";
	private static final String RAINBOW_THEME = "com.amitinside.e4.rcp.todo.rainbow";

	// Remember the state
	static boolean defaulttheme = true;

	// IThemeEngine can not be injected in case we use Jeeeyul CSS from
	// applicationCSS property (product extension point)
	// As a workaround, we can inject IThemeManager to get IThemeEngine and then
	// we can use it to set new theme dynamically

	@Execute
	public void switchTheme(IThemeManager manager) {
		final IThemeEngine engine = manager.getEngineForDisplay(Display
				.getCurrent());
		engine.setTheme(RAINBOW_THEME, true);
		if (!engine.getActiveTheme().getId().equals(DEFAULT_THEME)) {
			engine.setTheme(DEFAULT_THEME, true);
		} else {

			engine.setTheme(RAINBOW_THEME, true);
		}
	}
}
