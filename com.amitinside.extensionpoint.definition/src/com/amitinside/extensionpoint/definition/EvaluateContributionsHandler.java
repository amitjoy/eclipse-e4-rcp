package com.amitinside.extensionpoint.definition;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;

public class EvaluateContributionsHandler {
	private static final String IGREETER_ID = "com.amitinside.extensionpoint.definition.greeter";

	@Execute
	public void execute(final IExtensionRegistry registry) {
		evaluate(registry);
	}

	private void evaluate(final IExtensionRegistry registry) {
		final IConfigurationElement[] config = registry
				.getConfigurationElementsFor(IGREETER_ID);
		try {
			for (final IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof IGreeter) {
					executeExtension(o);
				}
			}
		} catch (final CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void executeExtension(final Object o) {
		final ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(final Throwable e) {
				System.out.println("Exception in client");
			}

			@Override
			public void run() throws Exception {
				((IGreeter) o).greet();
			}
		};
		SafeRunner.run(runnable);
	}
}
