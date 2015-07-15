package com.zwkj.soqs.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseController {
	protected static Log LOGGER;
	protected ControllerReturns returns;
	
	public BaseController(){
		LOGGER = LogFactory.getLog(this.getClass());
	}
	
}	
