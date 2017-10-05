package org.itstep.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories("org.itstep.dao")
@EnableTransactionManagement
@EnableAsync
@EntityScan("org.itstep")
@ConfigurationProperties
public class DbConfig{

	@Value(value = "${username}")
	private String username;

	@Value(value = "${password}")
	private String password;

	@Value(value = "${driver-class-name}")
	private String driverClassName;

	@Value(value = "${url}")
	private String url;

	@Value(value = "${hiber-option}")
	private String hiberOption;
	
//	@Value(value = "${database-name}")
//	private String dbName;
//
//	@Value(value = "${data-source-class-name}")
//	private String dataSourceClassName;
//
//	@Value(value = "${dialect}")
//	private String dialect;
//
//	@Value(value = "${schema}")
//	private String schema;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "org.itstep" });
		em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", hiberOption);
//		properties.setProperty("hibernate.dialect", dialect);
		return properties;
	}

    @Bean
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
//        dataSource.setSchema(schema);
        return dataSource;
    }
}
