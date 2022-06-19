package com.br.lis.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoLoggerAop {

	public void before(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.warn("Befor Method Excution--");
		
		Object[] objs = j.getArgs();
		if(objs !=null) {
			logger.warn("-----------{}-----------",j.getSignature().getName());
			for (int i = 0; i < objs.length; i++) {
				logger.warn(i+"th arg:\t"+String.valueOf(objs[i]));
			}
			logger.warn("-----------{}-----------",j.getSignature().getName());
		}
		
	}
	public void afterReturning(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.warn("End Method:\t{}",j.getSignature().getName());
	}
	public void afterThrowing(JoinPoint j, Exception exception) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.warn("Occur:\t{}",j.getSignature().getName());
		logger.warn("Occur:\t{}",exception.getMessage());
	}

}
