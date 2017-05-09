package com.nl.base.iBatis;

import java.io.IOException;

final public class ModuleFactory
{

	private String	dataSourceJNDI;

	public ModuleFactory(String dataSourceJNDI)
	{
		this.dataSourceJNDI = dataSourceJNDI;
		System.out.println(this.dataSourceJNDI);
	}

	public Module CreateModule() throws IOException
	{
		if (this.dataSourceJNDI == null) return null;
		return new BaseModule(this.dataSourceJNDI);
	}

}
