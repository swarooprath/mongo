package com.swaroopr.mongo.springinterceptor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInterceptMain {

	public static void main(String args[]) {
		try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml")) {
			Bean bean = applicationContext.getBean(Bean.class);
			bean.foo();
			applicationContext.close();
		}
	}
}
