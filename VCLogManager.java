package com.wowment.logging.api.impl;

import com.wowment.logging.api.VCLogger;

public class VCLogManager {
	
	public static VCLogger getLogger(Class<?> aClass){
		return VCLoggerImpl.getLogger(aClass);
	}
}
