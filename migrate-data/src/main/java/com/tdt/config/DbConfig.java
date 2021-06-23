package com.tdt.config;

import com.tdt.repository.BaseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DbConfig {

    @Configuration
    @Order(1)
    @RequiredArgsConstructor
    @EnableTransactionManagement
    @EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class,
            basePackages = {"com.tdt.domain", "com.tdt.model"})
    public class MasterDbConfig {

        private final Environment env;

        @Primary
        @Bean(name = "dataSource")
        public DataSource dataSource(){
            HikariDataSource dataSource = defaultHikariDatasource(env);
            dataSource.setDriverClassName(env.getProperty("datasource.master.driver-class-name"));
            dataSource.setJdbcUrl(env.getProperty("datasource.master.url"));
            dataSource.setUsername(env.getProperty("datasource.master.username"));
            dataSource.setPassword(env.getProperty("datasource.master.password"));
            dataSource.setPoolName("MASTER_CONNECTION_POOL");
            return dataSource;
        }

        @Primary
        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
            return getLocalContainerEntityManagerFactoryBean(dataSource(), "MASTER_PERSITENCE_UNIT", env);
        }

        @Primary
        @Bean
        public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory);
            transactionManager.setDataSource(dataSource());
            transactionManager.setJpaProperties(getJpaProperties(env));
            return transactionManager;
        }
    }

    @Configuration
    @Order(2)
    @RequiredArgsConstructor
    @EnableTransactionManagement
    @EnableJpaRepositories(entityManagerFactoryRef = "slaveEntityManagerFactory", transactionManagerRef = "slaveTransactionManager",
            repositoryBaseClass = BaseRepositoryImpl.class, basePackages = {"com.tdt.domain", "com.tdt.model"})
    public class SlaveDataSourceConfig {
        private final Environment env;

        @Bean(name = "slaveDataSource")
        public DataSource slaveDataSource() {
            HikariDataSource dataSource = defaultHikariDatasource(env);
            dataSource.setDriverClassName(env.getProperty("datasource.slave.driver-class-name"));
            dataSource.setJdbcUrl(env.getProperty("datasource.slave.url"));
            dataSource.setUsername(env.getProperty("datasource.slave.username"));
            dataSource.setPassword(env.getProperty("datasource.slave.password"));
            dataSource.setPoolName("SLAVE_CONNECTION_POOL");
            return dataSource;
        }

        @Bean(name = "slaveEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(){
            return getLocalContainerEntityManagerFactoryBean(slaveDataSource(), "SLAVE_PERSITENCE_UNIT", env);
        }

        @Bean(name = "slaveTransactionManager")
        public PlatformTransactionManager slaveTransactionManager(@Qualifier("slaveEntityManagerFactory") EntityManagerFactory slaveEntityManagerFactory){
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(slaveEntityManagerFactory);
            transactionManager.setDataSource(slaveDataSource());
            transactionManager.setJpaProperties(getJpaProperties(env));
            return transactionManager;
        }
    }

    private LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(DataSource dataSource,
                                                                                             String unitName, Environment environment){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"com.tdt.domain", "com.tdt.model"});
        em.setPersistenceUnitName(unitName);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getJpaProperties(environment));
        em.afterPropertiesSet();
        return em;
    }

    private Properties getJpaProperties(Environment environment) {
        return new Properties() {{
            put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
            put("hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
            put("hibernate.show-sql", environment.getProperty("spring.jpa.show-sql"));
            put("hibernate.jdbc.batch_size", environment.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
            put("hibernate.order_inserts", environment.getProperty("spring.jpa.properties.hibernate.order_inserts"));
            put("hibernate.order_updates", environment.getProperty("spring.jpa.properties.hibernate.order_updates"));
            put("hibernate.generate_statistics", environment.getProperty("spring.jpa.properties.hibernate.generate_statistics"));
        }};
    }

    private HikariDataSource defaultHikariDatasource(Environment environment){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMinimumIdle(Integer.valueOf(environment.getProperty("spring.datasource.hikari.minimum-idle")));
        dataSource.setMaximumPoolSize(Integer.valueOf(environment.getProperty("spring.datasource.hikari.maximum-pool-size")));
        dataSource.setConnectionTimeout(Integer.valueOf(environment.getProperty("spring.datasource.hikari.connection-timeout")));
        dataSource.setIdleTimeout(Integer.valueOf(environment.getProperty("spring.datasource.hikari.idle-timeout")));
        dataSource.setMaxLifetime(Integer.valueOf(environment.getProperty("spring.datasource.hikari.max-lifetime")));
        /**
         * HikariCP specific properties. Remove if you move to other connection pooling library.
         **/
        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", 25000);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 20048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        dataSource.addDataSourceProperty("initializationFailFast", true);
        return dataSource;
    }
}
