package com.swaroopr.mongo.javers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
 
@Configuration
@EnableMongoAuditing
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
