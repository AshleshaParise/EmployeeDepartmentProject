package com.cla.employeeportaldemo.exception;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler
{

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) 
	{
		System.out.println("Method Name:   "+method.getName()
		+"-----"+Arrays.toString(params)+"-----"+
				"error Message:  "+ex.getMessage()
				);
		
	}

}
