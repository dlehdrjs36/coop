package com.projectteam.coop.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@Slf4j
public class JpaConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public SpringProperties springProperties() {
        return new SpringProperties();
    }

    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource(SpringProperties springProperties) {
        log.info("{} ", springProperties.toString());
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(springProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(springProperties.getUrl());
        hikariConfig.setUsername(springProperties.getUsername());
        hikariConfig.setPassword(springProperties.getPassword());
        hikariConfig.setConnectionTimeout(150000);
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setPoolName("coop-mysql-pool");

        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "mysqlEntityManager")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder
            , @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        return entityManagerFactoryBuilder
                .dataSource(mysqlDataSource)
                .packages("com.projectteam.coop")
                .persistenceUnit("mysqlJpa")
                .build();
    }

    @Bean(name = "mysqlTxManager")
    public PlatformTransactionManager mainTransactionManager(@Qualifier("mysqlEntityManager") EntityManagerFactory mysqlEntityManager) {
        return new JpaTransactionManager(mysqlEntityManager);
    }
}
