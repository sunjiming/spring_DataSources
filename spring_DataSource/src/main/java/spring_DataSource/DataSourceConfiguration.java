package spring_DataSource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfiguration {

	// ==============================ds1 S======================================
	@Bean(name = "dataSourceDs1")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.ds1")
	public DataSource dataSourceDs1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplateDs1")
	public JdbcTemplate jdbcTemplateDs1(@Qualifier("dataSourceDs1") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	// ==============================ds1 E======================================

	// ==============================ds2 S======================================
	@Bean(name = "dataSourceDs2")
	@ConfigurationProperties(prefix = "spring.datasource.ds2")
	public DataSource dataSourceDs2() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplateDs2")
	public JdbcTemplate jdbcTemplateDs2(@Qualifier("dataSourceDs2") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	// ==============================ds2 E======================================
}
