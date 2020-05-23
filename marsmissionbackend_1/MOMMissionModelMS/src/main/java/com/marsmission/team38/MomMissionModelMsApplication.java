package com.marsmission.team38;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.marsmission.*")
public class MomMissionModelMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MomMissionModelMsApplication.class, args);
	}
}