package com.gn.sub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { 
		"com.gn.example.dao.service.impl"
		,"com.gn.example.dao.impl"
		,"com.gn.example.controller"
		}
	)
public class WebConfig extends WebMvcConfigurerAdapter{
	public final static String WEBCONFIG_PREFIX = "/pages/";
	public final static String WEBCONFIG_SUFFIX = ".jsp";
	public final static String RESOURCE_HANDLER = "/resources/";
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCE_HANDLER + "**").addResourceLocations(RESOURCE_HANDLER);
    }
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
	@Bean
	public InternalResourceViewResolver configureInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix(WEBCONFIG_PREFIX);
		resolver.setSuffix(WEBCONFIG_SUFFIX);
		return resolver;
	}
}
