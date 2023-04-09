/**
 * @author Suraj Adhikari <suraj.trent255@gmail.com>
 * Since March 11, 2023
 */
package com.ishanitech.iaccountingrest.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatasourceConfig {
	@Autowired
	DataSourceBean dsb;

	@Primary
	@Bean
	HikariDataSource datasource() {
		HikariConfig config = new HikariConfig();
		config.setUsername(dsb.getUsername());
		config.setPassword(dsb.getPassword());

		config.setDriverClassName(dsb.getDriverClassName());
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/iaccountingdb");
//		config.setJdbcUrl(String.format("jdbc:%s://localhost:%s/%s", dsb.getDatabaseType(), dsb.getPort(), dsb.getDatabaseName()));
		return new HikariDataSource(config);
	}

	@Bean
	TransactionAwareDataSourceProxy transactionAwareDataSourceProxy() {
		TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(datasource());
		return proxy;
	}

	@Bean
	PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(transactionAwareDataSourceProxy());
	}

	@Bean
	Jdbi jdbiBean() {
		Jdbi jdbi = Jdbi.create(transactionAwareDataSourceProxy());
		jdbi.installPlugin(new SqlObjectPlugin());
		return jdbi;
	}
}