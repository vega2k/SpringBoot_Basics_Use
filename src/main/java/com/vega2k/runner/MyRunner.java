package com.vega2k.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.vega2k.Vega2kProperties;

@Component
public class MyRunner implements ApplicationRunner {
	@Value("${vega2k.name}")
	private String name;
	
	@Autowired
    private String hello;

	private Logger logger = LoggerFactory.getLogger(MyRunner.class);
	
	@Autowired
	Vega2kProperties vega2kProperties;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(">>> foo : " + args.containsOption("foo"));
		System.out.println(">>>> bar : " + args.containsOption("bar"));
		
		logger.debug("^^^^^^^^^^^");
		logger.debug(name);
		logger.debug("^^^^^^^^^^^");
//		System.out.println(age);
		
		logger.debug(hello);
		
		logger.debug(vega2kProperties.getName());
				
	}
	
}
