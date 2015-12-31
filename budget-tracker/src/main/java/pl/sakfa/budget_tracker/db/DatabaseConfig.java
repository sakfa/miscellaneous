package pl.sakfa.budget_tracker.db;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;
import org.sql2o.Sql2o;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfig {
    @Bean
    public Sql2o sql2o() {
        return new Sql2o(dataSource());
    }

    private DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("sql/schema.sql")
                .build();
    }
}
