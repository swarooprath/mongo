package com.swaroopr.mongo.javers;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.api.JaversRepository;
import org.javers.repository.jql.QueryBuilder;
import org.javers.repository.mongo.MongoRepository;
import org.junit.Assert;

import com.mongodb.MongoClient;

public class JaversMongoMain {

	public static void main(String args[]) {

		
		   JaversRepository javersRepository = new MongoRepository(new MongoClient().getDB("test2"));
		   Javers javers = JaversBuilder.javers().registerJaversRepository(javersRepository).build();

	        // init your data
	        Person robert = new Person("bob", "Robert Martin");
	        // and persist initial commit
	        javers.commit("user", robert);

	        // do some changes
	        robert.setName("Robert C.");
	        // and persist another commit
	        javers.commit("user", robert);

	        // when:
	        List<CdoSnapshot> snapshots = javers.findSnapshots(
	            QueryBuilder.byInstanceId("bob", Person.class).build());

	        // then:
	        // there should be two Snapshots with Bob's state
	       Assert.assertEquals(2, snapshots.size());

	}
}
