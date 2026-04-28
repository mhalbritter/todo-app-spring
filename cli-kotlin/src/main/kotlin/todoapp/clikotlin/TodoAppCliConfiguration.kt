package todoapp.clikotlin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import todoapp.domain.TodoEntryRepository
import todoapp.domain.Todos
import todoapp.storage.datajdbc.StorageJdbcDataConfiguration

/**
 * @author Moritz Halbritter
 */
@Configuration(proxyBeanMethods = false)
@Import(StorageJdbcDataConfiguration::class)
class TodoAppCliConfiguration {
    @Bean
    fun todos(repository: TodoEntryRepository): Todos = Todos(repository)
}
