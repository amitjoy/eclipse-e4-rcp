package com.amitinside.extensionpoint.custom.factory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class MyFactory implements IExecutableExtensionFactory,
		IExecutableExtension {

	private String developer;

	@Override
	public void setInitializationData(IConfigurationElement config,
			String arg1, Object arg2) throws CoreException {

		developer = String.valueOf(config.getAttribute("developer"));
	}

	@Override
	public Object create() throws CoreException {
		return developer;
	}

}
