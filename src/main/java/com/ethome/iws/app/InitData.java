package com.ethome.iws.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InitData implements ApplicationListener<ApplicationEvent> {
	
	static final Logger logger = LoggerFactory.getLogger(InitData.class);

	public void onApplicationEvent(ApplicationEvent event) {
		logger.debug("system init........");
	}
}
