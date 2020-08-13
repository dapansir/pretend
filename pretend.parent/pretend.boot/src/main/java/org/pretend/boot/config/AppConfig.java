package org.pretend.boot.config;

import org.pretend.boot.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.pretend")
public class AppConfig {
	
	@Bean("app")
	public App getApp(){
		
		return new App();
	}

}
