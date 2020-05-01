package com.ds.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.ds.controller.*.*(..))")
	private void forControllerPackage() {
		System.out.println("Aspect for forControllerPackage");
	}
	
	@Pointcut("execution(* com.ds.service.*.*(..))")
	private void forServicePackage() {
		logger.info("Aspect for forServicePackage");
	}
	
	@Pointcut("execution(* com.ds.dao.*.*(..))")
	private void forDaoPackage() {
		logger.info("Aspect for forDaoPackage");
	}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
	private void forAppFlow() {
		logger.info("Aspect for forAppFlow");
	}
	
	@Before("forAppFlow()")
	public void before( JoinPoint joinPoint) {
		String methodSign = joinPoint.getSignature().toShortString();
		logger.info("Method Signature: " + methodSign);
		
		Object[] args = joinPoint.getArgs();
		
		for (Object arg : args) {
			logger.info("====>>Argument: " + arg);
		}
	}
	
	@AfterReturning(pointcut = "forAppFlow()",returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String method = joinPoint.getSignature().toShortString();
		
		logger.info("==afterReturning==> " + method);
		
		logger.info("====Result====> " + result);
	}
	
}
