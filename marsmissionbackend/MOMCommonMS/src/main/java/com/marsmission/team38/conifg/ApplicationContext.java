package com.marsmission.team38.conifg;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Configuration
@EnableAutoConfiguration
public class ApplicationContext {

	//variables for storing properties file path
	public static String propertyFilePath = System.getenv("property_path");
	public static String propertyFilePath1 = System.getenv("commons_path");

	private static Log logger = LogFactory.getLog(ApplicationContext.class);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		properties.setLocations(new FileSystemResource(propertyFilePath), new FileSystemResource(propertyFilePath1));
		properties.setIgnoreResourceNotFound(false);
		logger.info(propertyFilePath + "    " + propertyFilePath1);
		return properties;
	}

	//Method for getting global property
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