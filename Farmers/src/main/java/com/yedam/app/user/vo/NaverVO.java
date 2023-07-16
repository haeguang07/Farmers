package com.yedam.app.user.vo;

import java.lang.reflect.Field;

public class NaverVO extends Object{
	
	@Override
	public String toString() {
		String str="";
		 for (Field field : this.getClass().getDeclaredFields()) {
			 field.setAccessible(true);
		     Object value = null;
			try {
				value = field.get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			str +=   (String)value;
		 }
		 return str;
	}
	
}
