package com.zwkj.soqs.base;

public class SoqsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3320593988261097968L;

	public SoqsException(Throwable cause,String msg)
    {  
        super(msg,cause);  
    }
	
	public SoqsException(String msg)
    {  
        super(msg);  
    }
}
