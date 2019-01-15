package com.tslservices.flight.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = QDataTablesRepositoryFactoryBean.class, 
	basePackages = "com.tslservices.flight.repositories")
public class DataTablesConfiguration {

}
