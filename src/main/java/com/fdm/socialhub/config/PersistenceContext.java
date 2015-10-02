package com.fdm.socialhub.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by saurabh.jain on 14/09/2015.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.fdm.socialhub.repositories"})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class PersistenceContext {
    @Inject
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setShowSql(Boolean.valueOf(environment.getProperty("hibernate.show_sql")));
        vendorAdapter.setDatabase(Database.POSTGRESQL);

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.fdm.socialhub.entity");

        Properties jpaProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto",environment.getProperty("hibernate.hbm2ddl.auto"));

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
//        jpaProperties.put("hibernate.ejb.naming_strategy",environment.getProperty("hibernate.ejb.naming_strategy"));

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql",environment.getProperty("hibernate.show_sql"));

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql",environment.getProperty("hibernate.format_sql"));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSourceConfig = new BasicDataSource();
        dataSourceConfig.setDriverClassName(environment.getProperty("db.driver"));
        dataSourceConfig.setUrl(environment.getProperty("db.url"));
        dataSourceConfig.setUsername(environment.getProperty("db.username"));
        dataSourceConfig.setPassword(environment.getProperty("db.password"));

        return dataSourceConfig;
    }
}
