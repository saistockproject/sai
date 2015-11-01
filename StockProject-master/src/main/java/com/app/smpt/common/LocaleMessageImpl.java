package com.app.smpt.common;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocaleMessageImpl implements LocaleMessage, Serializable {

	private static final long serialVersionUID = 1L;
	private Locale locale;

	@Resource
	private MessageSource messageSource;

	public LocaleMessageImpl() {
		this.locale = Locale.US;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(String key) {
		try {
			return messageSource.getMessage(key, null, locale);
		} catch (Exception e) {
			return key;
		}
	}

	@Override
	public String getMessage(String key, String arg1) {
		try {
			return messageSource.getMessage(key, new Object[] { arg1 }, locale);
		} catch (Exception e) {
			return key;
		}
	}

	@Override
	public String getMessage(String key, String arg1, String arg2) {
		try {
			return messageSource.getMessage(key, new Object[] { arg1, arg2 },locale);
		} catch (Exception e) {
			return key;
		}
	}

	@Override
	public String getMessage(String key, Object[] valArray) {
		try {
			return messageSource.getMessage(key, valArray, locale);
		} catch (Exception e) {
			return key;
		}
	}

}
