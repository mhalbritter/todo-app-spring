package todoapp.storage.datajdbc;

import java.time.LocalDateTime;
import java.util.List;

import todoapp.domain.Priority;
import todoapp.domain.Status;
import todoapp.domain.TodoEntry;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

/**
 * @author Moritz Halbritter
 */
@Table("TODO")
record TodoEntryEntity(@Id long id, String title, String status, @Nullable String description, @Nullable String assignee, @Nullable String priority, @Nullable LocalDateTime dueDate) {
    static List<TodoEntry> toDomain(List<TodoEntryEntity> entities) {
        return entities.stream().map(TodoEntryEntity::toDomain).toList();
    }

    static TodoEntry toDomain(TodoEntryEntity entity) {
        return new TodoEntry(
            TodoEntry.Id.of(entity.id()),
            entity.title(),
            Status.valueOf(entity.status()),
            entity.description(),
            entity.assignee(),
            (entity.priority() == null) ? null : Priority.valueOf(entity.priority()),
            entity.dueDate()
        );
    }

    static TodoEntryEntity fromDomain(TodoEntry todoEntry) {
        return new TodoEntryEntity(
            todoEntry.id().id(),
            todoEntry.title(),
            todoEntry.status().name(),
            todoEntry.description(),
            todoEntry.assignee(),
            (todoEntry.priority() == null) ? null : todoEntry.priority().name(),
            todoEntry.dueDate()
        );
    }
}
