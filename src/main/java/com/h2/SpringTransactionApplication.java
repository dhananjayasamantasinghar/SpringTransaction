package com.h2;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class SpringTransactionApplication {

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String db_url;

	@Value("${spring.datasource.username}")
	private String db_username;

	@Value("${spring.datasource.password}")
	private String db_password;

	@Value("${spring.jpa.database-platform}")
	private String db_dialect;

	public static void main(String[] args) {
		SpringApplication.run(SpringTransactionApplication.class, args);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", db_dialect);
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(db_url);
		dataSource.setUsername(db_username);
		dataSource.setPassword(db_password);
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}

	/*
	 * @Bean public PlatformTransactionManager transactionManager() {
	 * JpaTransactionManager transactionManager = new JpaTransactionManager();
	 * transactionManager.setEntityManagerFactory(sessionFactory().getObject());
	 * return transactionManager; }
	 */

}
