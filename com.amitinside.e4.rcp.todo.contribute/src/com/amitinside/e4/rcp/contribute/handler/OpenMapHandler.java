package com.amitinside.e4.rcp.contribute.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public class OpenMapHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		final ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, new LabelProvider());
		dialog.setMultipleSelection(false);
		dialog.setHelpAvailable(true);
		dialog.setAllowDuplicates(false);
		dialog.setStatusLineAboveButtons(true);
		dialog.setMessage("Input pattern(support ? and *):");
		dialog.setTitle("Select the string:");
		dialog.setElements(new Object[] { "aa", "bb", "cc", "dd" });
		if (dialog.open() == Window.OK) {
			System.out.println(dialog.getFirstResult());
		}

	}
}
