package com.cla.employeeportaldemo.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.cla.employeeportaldemo.exception.AsyncExceptionHandler;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class AsyncConfiguration extends AsyncConfigurerSupport{
	 private static final Logger logger = LogManager.getLogger(AsyncConfiguration.class);
	 
	@Autowired
	private AsyncExceptionHandler asyncExceptionHandler;
    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
    	logger.debug("Creating Async Task Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("DepartmentThread-");
        executor.initialize();
        return executor;
    }
    
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler()
    {
    	return asyncExceptionHandler;
    }
}