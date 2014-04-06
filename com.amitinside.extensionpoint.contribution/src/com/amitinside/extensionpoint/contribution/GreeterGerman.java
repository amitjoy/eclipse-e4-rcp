package com.amitinside.extensionpoint.contribution;

import com.amitinside.extensionpoint.definition.IGreeter;

public class GreeterGerman implements IGreeter {

	@Override
	public void greet() {
		System.out.println("Moin, moin!");
	}
}