package com.amitinside.e4.todo.contribute;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;

import com.amitinside.e4.todo.contribute.handler.ExitHandlerWithCheck;

public class MenuProcessor {

	// I get this via the parameter
	// of the process definition
	@Inject
	@Named("org.eclipse.ui.file.menu")
	private MMenu menu;

	@Execute
	public void execute() {
		// Remove the old exit menu entry
		if (menu != null && menu.getChildren() != null) {
			List<MMenuElement> list = new ArrayList<MMenuElement>();
			for (MMenuElement element : menu.getChildren()) {
				// Separaters have no label hence we
				// need to check for null
				if (element.getLabel() != null) {
					if (element.getLabel().contains("Exit")) {
						list.add(element);
					}
				}
			}
			menu.getChildren().removeAll(list);

			// Now add a new menu entry
			MDirectMenuItem menuItem = MMenuFactory.INSTANCE
					.createDirectMenuItem();
			menuItem.setLabel("Another Exit");
			menuItem.setContributionURI("bundleclass://"
					+ "com.amitinside.e4.rcp.todo.contribute/"
					+ ExitHandlerWithCheck.class.getName());
			menu.getChildren().add(menuItem);
		}

	}
}
