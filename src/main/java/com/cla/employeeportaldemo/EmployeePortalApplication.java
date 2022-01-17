package com.cla.employeeportaldemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching 
@SpringBootApplication
@ComponentScan(basePackages = "com.cla.*")
@EntityScan("com.cla.*")
@EnableJpaRepositories("com.cla.employeeportaldemo.repository")
public class EmployeePortalApplication {
	
	@Bean
	public RestTemplate getRestTemplate() 
	{
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeePortalApplication.class, args);
	}
	
}
