package com.teja.appointment.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

	@Bean
	public NamedParameterJdbcTemplate namedParameterJDBCTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	
	
}
