package com.app.smpt.common;

import org.springframework.context.MessageSourceAware;

public interface LocaleMessage  extends MessageSourceAware{

	public  String getMessage(String key);
	public  String getMessage(String key , String arg1);
	public  String getMessage(String key , String arg1 , String arg2);
	public  String getMessage(String key , Object[] valArray) ;	
}
