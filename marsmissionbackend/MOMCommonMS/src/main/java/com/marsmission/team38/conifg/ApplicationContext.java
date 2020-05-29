package com.marsmission.team38.conifg;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableAutoConfiguration
public class ApplicationContext {

	public static String propertyFilePath = System.getenv("property_path");
	public static String propertyFilePath1 = System.getenv("commonms_path");

//	private static Log logger = LogFactory.getLog(ApplicationContext.class);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		properties.setLocations(new FileSystemResource(propertyFilePath), new FileSystemResource(propertyFilePath1));
		properties.setIgnoreResourceNotFound(false);
		System.out.println(propertyFilePath + "    " + propertyFilePath1);
		return properties;
	}
}