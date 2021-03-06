package com.marsmission.team38.conifg;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableAutoConfiguration
public class ApplicationContext {

	// variables for storing properties file path
	public static String propertyFilePath = System.getenv("property_path");
	public static String propertyFilePath1 = System.getenv("mission_path");

	private static Log logger = LogFactory.getLog(ApplicationContext.class);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		properties.setLocations(new FileSystemResource(propertyFilePath), new FileSystemResource(propertyFilePath1));
		properties.setIgnoreResourceNotFound(false);
		logger.info(propertyFilePath + "    " + propertyFilePath1);
		// properties.setOrder(0);
		return properties;
	}

	
	// Method for getting global property
	public static String getGlobalProperty(String propertyName) {
		Properties properties = new Properties();
		String propertyValue = "";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(propertyFilePath);
			if (inputStream != null) {
				properties.load(inputStream);
				propertyValue = properties.getProperty(propertyName); // getting property value from properties file
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return propertyValue;
	}

}