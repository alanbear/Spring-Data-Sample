package alanbear.sample.spring.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="sampleEntityManagerFactory",
        transactionManagerRef="sampleTransactionManager",
        basePackages= { "alanbear.sample.spring.data.repository" })
public class RepositoryConfig {

    @Autowired
    @Qualifier("dataSource")
    private DataSource sampleDataSource;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JpaProperties jpaProperties;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Primary
    @Bean(name = "sampleEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "sampleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(sampleDataSource)
                .properties(getVendorProperties(sampleDataSource))
                .packages("alanbear.sample.spring.data.model")
                .persistenceUnit("samplePersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Primary
    @Bean(name = "sampleTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}
