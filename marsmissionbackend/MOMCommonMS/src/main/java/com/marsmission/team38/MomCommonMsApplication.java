package com.marsmission.team38;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.marsmission.team38.conifg.ApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.marsmission.*")
public class MomCommonMsApplication {
	static String logDirectory = ApplicationContext.getGlobalProperty("logging.file");

	public static void main(String[] args) {
		//SpringApplication object created for common
		SpringApplication springApplication = new SpringApplication(MomCommonMsApplication.class);
		Properties properties = new Properties();
		properties.put("logging.file", logDirectory); //setting logging file and directory
		springApplication.setDefaultProperties(properties);  //setting properties
		springApplication.run(args);
	}

}
