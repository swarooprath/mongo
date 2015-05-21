package com.swaroopr.mongo.springdata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
 
@Configuration
@EnableMongoAuditing
@ComponentScan(basePackages = "org.javers.spring.repository.mongo")
@EnableAspectJAutoProxy
@EnableMongoRepositories(basePackages = "org.javers.spring.repository.mongo")
public class SpringMongoConfig extends AbstractMongoConfiguration {
 
	@Override
	public String getDatabaseName() {
		return "test2";
	}	
 
	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}
	
	@Bean
	  public AuditorAware<User> myAuditorProvider() {
	      return new MyAuditorAware();
	  }
	
	private static final class MyAuditorAware implements AuditorAware<User> {
		
		private volatile int counter = 0;
		
		public MyAuditorAware() {
			
		}

		@Override
		public User getCurrentAuditor() {
			return new User(String.format("srath-%d", counter++));
		}
	}
}
