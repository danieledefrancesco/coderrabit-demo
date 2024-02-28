package com.tuimm.learningpath;

import com.tuimm.learningpath.transactions.TransactionTemplateFactory;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.net.http.HttpClient;

@Configuration
@ComponentScan("com.tuimm.learningpath")
@PropertySource(value="classpath:infrastructure.properties")
public class InfrastructureConfig {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }

    @Bean
    TransactionTemplateFactory transactionManagerFactory(EntityManagerFactory entityManagerFactory) {
        return () -> new TransactionTemplate(new JpaTransactionManager(entityManagerFactory));
    }
}
