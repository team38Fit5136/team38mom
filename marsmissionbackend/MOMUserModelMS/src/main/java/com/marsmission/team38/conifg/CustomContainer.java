package com.marsmission.team38.conifg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Value("${mission.server.port}")
	private int port;

	@Override
	public void customize(ConfigurableServletWebServerFactory container) {

		container.setPort(port);

	}

}
