package todoapp.storage.datajdbc;

import java.util.List;

import todoapp.domain.Priority;
import todoapp.domain.TodoEntry;
import todoapp.domain.TodoEntryRepository;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.lang.Nullable;

/**
 * @author Moritz Halbritter
 */
class SpringDataJdbcTodoEntryRepository implements TodoEntryRepository {
    private final TodoEntryEntityRepository repository;

    private final JdbcAggregateOperations jdbcAggregateOperations;

    SpringDataJdbcTodoEntryRepository(TodoEntryEntityRepository repository, JdbcAggregateOperations jdbcAggregateOperations) {
        this.repository = repository;
        this.jdbcAggregateOperations = jdbcAggregateOperations;
    }

    @Override
    public TodoEntry.Id generateId() {
        Long maxId = this.repository.findMaxId();
        return (maxId == null) ? TodoEntry.Id.of(1) : TodoEntry.Id.of(maxId + 1);
    }

    @Override
    public List<TodoEntry> findAllEntries() {
        return TodoEntryEntity.toDomain(this.repository.findAll());
    }

    @Override
    public @Nullable TodoEntry findWithId(TodoEntry.Id id) {
        return this.repository.findById(id.id()).map(TodoEntryEntity::toDomain).orElse(null);
    }

    @Override
    public void insertEntry(TodoEntry todoEntry) {
        // Force an insert, because we generate the IDs ourselves
        this.jdbcAggregateOperations.insert(TodoEntryEntity.fromDomain(todoEntry));
    }

    @Override
    public void updateEntry(TodoEntry todoEntry) {
        this.repository.save(TodoEntryEntity.fromDomain(todoEntry));
    }

    @Override
    public void deleteEntry(TodoEntry todoEntry) {
        this.repository.deleteById(todoEntry.id().id());
    }

    @Override
    public List<TodoEntry> findWithPriority(@Nullable Priority priority) {
        return TodoEntryEntity.toDomain(this.repository.findAllByPriority((priority == null) ? null : priority.name()));
    }
}
