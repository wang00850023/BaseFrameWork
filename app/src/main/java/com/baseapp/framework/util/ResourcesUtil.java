package com.baseapp.framework.util;

import java.lang.reflect.Field;

import android.content.Context;
/**
 * asd
 * @author deyi
 */
public class ResourcesUtil {

	@SuppressWarnings("rawtypes")
	public static int getResourceId(String resourceName)

	{
		int id = 0;
		try {
			String[] splitStr = resourceName.split("\\.");
			String classStr = splitStr[0] + "$" + splitStr[1];
			Class c = Class.forName("com.wby.baseapp.czl" + "." + classStr);
			Field f = c.getDeclaredField(splitStr[2]);
			id = f.getInt(f.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public static int getResourceId(Context context, String resourceName,
			String type) {
		return context.getResources().getIdentifier(resourceName, type,
				"com.wby.baseapp.czl");
	}
}
