/**
 * 
 */
package com.app.smpt.common;

/**
 * @author anand.mohan
 * 
 */
public interface SysLogger{
	
	public void error(final Throwable t);

	public void error(final Throwable t,final String  logMsg);
	
	public void info( String  logMsgWithParam,Object[] logMsgParam);
	
	public void info( String  logMsg);
	
	public void debug( String  logMsg);
	
	public void debug( String  logMsgWithParam,Object[] logMsgParam);
	
	public void warning( String  logMsg);
	
	public void warning( String  logMsgWithParam,Object[] logMsgParam);
}
