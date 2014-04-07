package com.amitinside.e4.rcp.todo.lifecycle;

import javax.inject.Inject;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.mihalis.opal.login.LoginDialog;
import org.mihalis.opal.login.LoginDialogVerifier;
import org.osgi.service.prefs.BackingStoreException;

public class NewManager {

	@Inject
	@Preference(nodePath = "com.amitinside.e4.rcp.todo", value = "user")
	private String user;

	@PostContextCreate
	public void postContextCreate(@Preference IEclipsePreferences prefs,
			IApplicationContext appContext, Display display) {

		final Shell shell = new Shell(SWT.TOOL | SWT.NO_TRIM);

		LoginDialog dialog = new LoginDialog();
		final LoginDialogVerifier verifier = new LoginDialogVerifier() {

			@Override
			public void authenticate(final String login, final String password)
					throws Exception {
				if ("".equals(login)) {
					throw new Exception("Please enter a login.");
				}

				if ("".equals(password)) {
					throw new Exception("Please enter a password.");
				}

				if (!login.equalsIgnoreCase("amit")) {
					throw new Exception("Login unknown.");
				}

				if (!password.equalsIgnoreCase("amit")) {
					throw new Exception(
							"Authentication failed, please check your password.");
				}

			}
		};

		dialog.setVerifier(verifier);
		
		// close the static splash screen
		appContext.applicationRunning();

		// position the shell
		setLocation(display, shell);

		// open the dialog
		if (!dialog.open()) {
			// close the application
			System.exit(-1);
		} else {
			// Store the user values in the preferences
			prefs.put("user", dialog.getLogin());
			try {
				prefs.flush();
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}

	}

	private void setLocation(Display display, Shell shell) {
		Monitor monitor = display.getPrimaryMonitor();
		Rectangle monitorRect = monitor.getBounds();
		Rectangle shellRect = shell.getBounds();
		int x = monitorRect.x + (monitorRect.width - shellRect.width) / 2;
		int y = monitorRect.y + (monitorRect.height - shellRect.height) / 2;
		shell.setLocation(x, y);
	}
}