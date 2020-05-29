package com.marsmission.team38;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.marsmission.*")
public class MomCommonMsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(MomCommonMsApplication.class);
		Properties properties = new Properties();
		springApplication.setDefaultProperties(properties);
		springApplication.run(args);
	}

}
