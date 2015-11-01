package com.app.smpt.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringAppContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * Loaded during Spring initialization.
	 */
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		applicationContext = ctx;
	}

	/**
	 * Get access to the Spring ApplicationContext from anywhere in application.
	 */
	public static ApplicationContext getContext() {
		return applicationContext;
	}
	
	/**
	 * Get a bean.
	 */
	@SuppressWarnings("unchecked")
	public static Object getBean(@SuppressWarnings("rawtypes") Class clazz) {
		return getContext().getBean(clazz);
		
	}
	
	/**
	 * Get a bean.
	 */
	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);		
	}
	
	
}