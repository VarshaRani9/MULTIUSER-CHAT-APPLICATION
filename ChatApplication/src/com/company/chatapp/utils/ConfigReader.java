package com.company.chatapp.utils;

import java.util.ResourceBundle;

public interface ConfigReader {
	public static final ResourceBundle rb = ResourceBundle.getBundle("config");
	public static String getValue(String key) {
		return rb.getString(key);
	}
}
