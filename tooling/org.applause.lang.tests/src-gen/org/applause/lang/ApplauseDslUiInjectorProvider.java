/*
* generated by Xtext
*/
package org.applause.lang;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class ApplauseDslUiInjectorProvider implements IInjectorProvider {
	
	public Injector getInjector() {
		return org.applause.lang.ui.internal.ApplauseDslActivator.getInstance().getInjector("org.applause.lang.ApplauseDsl");
	}
	
}