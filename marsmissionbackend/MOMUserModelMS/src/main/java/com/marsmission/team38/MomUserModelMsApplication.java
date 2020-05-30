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
public class MomUserModelMsApplication {
	static String logDirectory = ApplicationContext.getGlobalProperty("logging.file");
	
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(MomUserModelMsApplication.class);
		Properties properties = new Properties();
		properties.put("logging.file", logDirectory);
		springApplication.setDefaultProperties(properties);
		springApplication.run(args);
	}

}
