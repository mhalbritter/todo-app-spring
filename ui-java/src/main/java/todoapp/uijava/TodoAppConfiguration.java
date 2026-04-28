package todoapp.uijava;

import todoapp.domain.TodoEntryRepository;
import todoapp.domain.Todos;
import todoapp.storage.datajdbc.StorageJdbcDataConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Moritz Halbritter
 */
@Configuration(proxyBeanMethods = false)
@Import(StorageJdbcDataConfiguration.class)
class TodoAppConfiguration {
    @Bean
    Todos todos(TodoEntryRepository todoEntryRepository) {
        return new Todos(todoEntryRepository);
    }
}
