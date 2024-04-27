package com.edu.uco.pch.crosscutting.helpers;

public final class ObjectHelper {
	
	private static final ObjectHelper INSTANCE = new ObjectHelper();
	
	private ObjectHelper() {
		super();
	}
	
	public static final ObjectHelper getObjecHelper() {
		return INSTANCE;
	}
	
	public <t> boolean isNull(t objeto) {
		return objeto == null;
	}
	
	public <t> t getDefaultValue(t objeto, t valorDefecto) {
		return isNull(objeto) ? valorDefecto : objeto;
	}
	
	

}
