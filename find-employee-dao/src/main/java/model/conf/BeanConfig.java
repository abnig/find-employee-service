package model.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "model" })
@ComponentScan("model")
@PropertySource("application.properties")
public class BeanConfig {
	
	@Autowired
	private Environment environment;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("model");
		entityManagerFactoryBean.setPersistenceUnitName("entityManager");
		return entityManagerFactoryBean;
	}

	
	@Bean
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
		hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
		hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
		hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
		Properties dsProperties = new Properties();
		dsProperties.setProperty("serverTimezone",environment.getProperty("serverTimezone"));
		dsProperties.setProperty("useSSL", environment.getProperty("useSSL"));
		dsProperties.setProperty("verifyServerCertificate", environment.getProperty("verifyServerCertificate"));
		hikariConfig.setDataSourceProperties(dsProperties);
		DataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}

}
