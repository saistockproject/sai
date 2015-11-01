package com.app.smpt.common.exception;

import org.springframework.orm.hibernate3.HibernateJdbcException;

import com.app.smpt.common.LocaleMessage;
import com.app.smpt.common.SysLogger;
import com.app.smpt.common.SysLoggerFactory;
import com.app.smpt.utils.SpringAppContext;
import com.app.smpt.utils.SysUtilities;


public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	protected LocaleMessage LocaleMessage = (LocaleMessage) SpringAppContext.getBean(LocaleMessage.class);
	private String message;
	public SysLogger logger = SysLoggerFactory.getLogger(this.getClass());

	public SystemException(String errorMessageKey, String errorMessageParam[], Exception e) {
		super(e);
		if( e instanceof HibernateJdbcException ){
			resolveMessage( "generic.error.occured.with.message", new String[] { ((HibernateJdbcException) e).getSQLException().getMessage()});
		} else {
			resolveMessage(errorMessageKey, errorMessageParam);
		}
		logger.error(e, message);

	}

	public SystemException(String errorMessageKey, String[] errorMessageParam) {
		super();
		resolveMessage(errorMessageKey, errorMessageParam);
	}

	public SystemException(String errorMessageKey) {
		super();
		resolveMessage(errorMessageKey, null);
	}

	public SystemException(String errorMessageKey, Exception e) {
		super(e);
		resolveMessage(errorMessageKey, null);
		logger.error(e, message);

	}

	private void resolveMessage(String errorMessageKey, String[] errorMessageParam) {
		try {
			message = LocaleMessage.getMessage(errorMessageKey, errorMessageParam);
		} catch (Exception ex) {
			message = errorMessageKey;
		}
	}
	
	@Override
	public String getMessage() {
		String errorMsg = super.getMessage();
		if(SysUtilities.isEmpty(message) ){
			message = errorMsg;
		}
		return message;
	}

}
