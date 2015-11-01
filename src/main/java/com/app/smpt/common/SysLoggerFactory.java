package com.app.smpt.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.app.smpt.model.User;
import com.app.smpt.utils.SysUtilities;





public class SysLoggerFactory {
	
	public static SysLogger getLogger(Class<?> clazz){
		
		Logger logger = LoggerFactory.getLogger(clazz);
		User user = null;
		
	/* ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
				if(!SysUtilities.isEmpty(attr.getSessionId())){
					user = (User) attr.getSessionId()get(SysConstants.LOGGED_IN_USER);
			}
			
		if(!com.app.smpt.utils.SysUtilities.isEmpty(user)){
			return new SysLoggerImpl(logger, user.getUserName());
		}else{
			return new SysLoggerImpl(logger);
		}
	}*/
		return null;
	
}
}