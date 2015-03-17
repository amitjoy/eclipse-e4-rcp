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
