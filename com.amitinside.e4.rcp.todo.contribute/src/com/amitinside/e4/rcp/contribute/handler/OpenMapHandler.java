package com.amitinside.e4.rcp.contribute.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenMapHandler {

	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "Test", "Just testing");
	}
}
