package com.gn.sub.config;

import java.util.Properties;

import org.hibernate.engine.transaction.internal.jta.CMTTransactionFactory;
import org.hibernate.transaction.TransactionManagerLookup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.gn.example.dao.service.impl",entityManagerFactoryRef = "entityManagerFactory1",transactionManagerRef = "springTransactionManager")
public class JpaHibernateAConfig {
	
	@Bean(name = "dataSource1", initMethod = "init" , destroyMethod = "close")
	public AtomikosDataSourceBean setAtomikosDataSourceBean() {
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setUniqueResourceName("DataSource1");
		atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		atomikosDataSourceBean.setPoolSize(10);
		Properties p = new Properties();
		p.setProperty("user","root");
		p.setProperty("password", "1234");
		p.setProperty("url", "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf-8");
		p.setProperty("pinGlobalTxToPhysicalConnection", "true");
		atomikosDataSourceBean.setXaProperties(p);
		return atomikosDataSourceBean;
	}
	@Bean(name = "entityManagerFactory1")
	@DependsOn("springTransactionManager")
	public LocalContainerEntityManagerFactoryBean setLocalContainerEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.TRUE);
		hibernateJpaVendorAdapter.setShowSql(Boolean.TRUE);
		hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		Properties jpaProperties = new Properties();
		jpaProperties.put("javax.persistence.transactionType", "JTA");
		jpaProperties.put("hibernate.transaction.manager_lookup_class", "com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup");
		jpaProperties.put("hibernate.transaction.factory_class", CMTTransactionFactory.class);
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.setDataSource(setAtomikosDataSourceBean());
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("PersistenceUnit1");
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.gn.example.dao.entity");
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		localContainerEntityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return localContainerEntityManagerFactoryBean;
	}
	
	
}
