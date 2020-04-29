package com.marsmission.team38;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("com.marsmission.team38")
@EnableAsync(proxyTargetClass = true)
public class MarsmissionbackendApplication {
//	static String logDirectory = ApplicationContext.getGlobalProperty("logging.file");

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(MarsmissionbackendApplication.class);

		Properties properties = new Properties();
//		properties.put("logging.file", logDirectory);
		springApplication.setDefaultProperties(properties);
		springApplication.run(args);
	}

}
