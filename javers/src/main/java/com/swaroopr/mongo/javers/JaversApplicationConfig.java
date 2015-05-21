package com.swaroopr.mongo.javers;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.mongo.MongoRepository;
import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.aspect.JaversAuditableRepositoryAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages = "org.javers.spring.repository.mongo")
@EnableAspectJAutoProxy
@EnableMongoRepositories(basePackages = "org.javers.spring.repository.mongo")
public class JaversApplicationConfig {

    /**
     * Creates JaVers instance backed by {@link MongoRepository}
     */
    @Bean
    public Javers javers() {

    	MongoRepository mongoRepo = new MongoRepository(mongoDB());
    	return JaversBuilder.javers().registerJaversRepository(mongoRepo).build();
    }

    /**
     * MongoDB setup
     */
    @Bean
    @SuppressWarnings({ "deprecation", "resource" })
    public DB mongoDB(){
        return new MongoClient().getDB("test");
    }

    /**
     * Enables Repository auto-audit aspect. <br/>
     *
     * Use {@link org.javers.spring.annotation.JaversSpringDataAuditable}
     * to annotate Spring Data Repositories
     * or {@link org.javers.spring.annotation.JaversAuditable} for ordinary Repositories.
     */
    @Bean
    public JaversAuditableRepositoryAspect javersAuditableRepositoryAspect() {
        return new JaversAuditableRepositoryAspect(javers(), authorProvider());
    }

    /**
     * Required by Repository auto-audit aspect. <br/><br/>
     *
     * Returns mock implementation for testing.
     * <br/>
     * Provide real implementation,
     * when using Spring Security you can use
     * {@link org.javers.spring.auditable.SpringSecurityAuthorProvider}.
     */
    @Bean
    public AuthorProvider authorProvider() {
        return new AuthorProvider() {
            @Override
            public String provide() {
                return "abcd";
            }
        };
    }
}
