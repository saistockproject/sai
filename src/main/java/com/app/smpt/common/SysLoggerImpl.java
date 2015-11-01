package com.app.smpt.common;

import org.slf4j.Logger;


public class SysLoggerImpl implements  SysLogger{


	private Logger lLogger ;
	private String username;
	
	public SysLoggerImpl(Logger lLogger){
		this.lLogger = lLogger;
		this.username = "";
	}

	public SysLoggerImpl(Logger lLogger, String username){
		this.lLogger = lLogger;
		this.username = "[" + username + "] - ";
	}
	
	@Override
	public void error(final Throwable t) {
		
		lLogger.error(username + t.getMessage(), t);
	}
	
	@Override
	public void error(final Throwable t,final String  logMsg) {
		
		lLogger.error(username + logMsg, t);
	}
	
	@Override
	public void info( String  logMsgWithParam,Object[] logMsgParam){
		
		lLogger.info(username + logMsgWithParam, logMsgParam);
	}
	@Override
	public void info( String  logMsg){
		
		lLogger.info(username + logMsg);
	}
	@Override
	public void debug( String  logMsg){
		
		lLogger.debug(username + logMsg);
	}
	
	@Override
	public void debug( String  logMsgWithParam,Object[] logMsgParam){

		lLogger.debug(username + logMsgWithParam,logMsgParam);
	}
	
	@Override
	public void warning( String  logMsg){
		
		lLogger.warn(username + logMsg);
	}
	
	@Override
	public void warning( String  logMsgWithParam,Object[] logMsgParam){
		
		lLogger.warn(username + logMsgWithParam,logMsgParam);
	}
}
