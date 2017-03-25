package com.thinkman.springboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkman.springboot.mapper.QuerySelect;

@Controller
@ComponentScan("com.thinkman")
@EnableAutoConfiguration
public class MyApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{

	@Autowired
	QuerySelect querySelect;

	private static Logger logger = Logger.getLogger(MyApplication.class);


	@RequestMapping("/")
	@ResponseBody
	String home() {
		System.out.println(":::::::::"+querySelect.queryCount());
		logger.info("Hello World!!!");
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(MyApplication.class, args);
        if (context instanceof EmbeddedWebApplicationContext) {
            int port = ((EmbeddedWebApplicationContext) context).getEmbeddedServletContainer().getPort();
            String contextPath = context.getApplicationName();
        }
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer arg0) {
		arg0.setPort(8081);
	}

}
