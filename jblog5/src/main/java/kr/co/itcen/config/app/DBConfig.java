package kr.co.itcen.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement			//transactional 쓰기위해 필요
@PropertySource("classpath:kr/co/itcen/config/app/properties/jdbc.properties")
public class DBConfig {
	@Autowired
	Environment environment; 
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource(); 
		basicDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		basicDataSource.setInitialSize(environment.getProperty("jdbc.initialSize", Integer.class));
		basicDataSource.setMaxActive(environment.getProperty("jdbc.maxActive", Integer.class));
		
		return basicDataSource;
		
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}