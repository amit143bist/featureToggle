/**
 * 
 */
package com.docusign.featureToggleService;

import java.util.Map;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.web.FF4jProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * @author Amit.Bist
 *
 */
public class MyFF4jProvider implements FF4jProvider {

	@Autowired
	private FF4j ff4j;

	/**
	 * 
	 */
	public MyFF4jProvider() {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		System.out.println("MyFF4jProvider.MyFF4jProvider()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ff4j.web.FF4jProvider#getFF4j()
	 */
	@Override
	public FF4j getFF4j() {

		System.out.println("MyFF4jProvider.getFF4j()");
		
		Map<String, Feature> featureMap = ff4j.getFeatureStore().readAll();
		System.out.println("MyFF4jProvider.getFF4j()- " + featureMap.toString());
		return ff4j;
	}

}