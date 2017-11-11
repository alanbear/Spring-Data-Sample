package alanbear.sample.spring.data.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * DataSourceBuilder
 *
 * 參考 java匯入資料方式
 * http://stackoverflow.com/questions/16038360/initialize-database-without-xml-configuration-but-using-configuration
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.dbcp2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(BasicDataSource.class).build();
    }

    @Bean
    public JdbcTemplate sampleJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
