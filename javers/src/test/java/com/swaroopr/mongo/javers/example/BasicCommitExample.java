package com.swaroopr.mongo.javers.example;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.junit.Test;

import java.util.List;

import junit.framework.Assert;

public class BasicCommitExample {
    @Test
    public void shouldCommitToJaversRepository() {
        //given:

        // prepare JaVers instance. By default, JaVers uses InMemoryRepository,
        // it's useful for testing
        Javers javers = JaversBuilder.javers().build();

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
