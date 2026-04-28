package todoapp.storage.datajdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * @author Moritz Halbritter
 */
@Configuration(proxyBeanMethods = false)
@EnableJdbcRepositories(basePackageClasses = TodoEntryEntityRepository.class)
public class StorageJdbcDataConfiguration {
    @Bean
    SpringDataJdbcTodoEntryRepository springDataJdbcTodoEntryRepository(TodoEntryEntityRepository todoEntryEntityRepository, JdbcAggregateOperations jdbcAggregateOperations) {
        return new SpringDataJdbcTodoEntryRepository(todoEntryEntityRepository, jdbcAggregateOperations);
    }
}
