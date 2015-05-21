package com.swaroopr.mongo.springdata;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class SpringMongoMain {

	public static void main(String args[]) {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class)) {
			MongoOperations mongoOperation = (MongoOperations) ctx
					.getBean("mongoTemplate");

			Issue issue = new Issue("elementum", "signed up tesla.");

			// save
			mongoOperation.insert(issue);

			// now user object got the created id.
			System.out.println("1. user : " + issue);

			// query to search user
			Query searchUserQuery = new Query(Criteria.where("title").is(
					"elementum"));

			// find the saved user again.
			Issue savedIssue = mongoOperation
					.findOne(searchUserQuery, Issue.class);
			System.out.println("2. find - savedUser : " + savedIssue);

			// update password
			mongoOperation.save(issue);

			// find the updated user object
			Issue updatedIssue = mongoOperation.findOne(searchUserQuery,
					Issue.class);

			System.out.println("3. updatedUser : " + updatedIssue);

			// delete
			mongoOperation.remove(searchUserQuery, Issue.class);

			// List, it should be empty now.
			List<Issue> listIssues = mongoOperation.findAll(Issue.class);
			System.out.println("4. Number of issues = " + listIssues.size());
		}

	}

}
