package com.edu.uco.pch.crosscutting.helpers;

public final class TextHelper {

	public static final String EMPTY = "";

	private TextHelper() {
		super();
	}

	public static final boolean isNull(final String string) {
		return string == null;
	}

	public static final boolean isNullorEmpty(final String string) {
		return isNull(string) || EMPTY.equals(string.trim());
	}

	public static final String getDefaultValue(final String string, final String defaultValue) {
		return isNullorEmpty(string) ? defaultValue : string;
	}

	public static final String getDefaulValue(final String string) {
		return getDefaultValue(string, EMPTY);
	}

	public static final String applyTrim(final String string) {
		return getDefaulValue(string).trim();
	}
}
