package todoapp.domain;

import java.util.List;

import org.springframework.lang.Nullable;

/**
 * @author Moritz Halbritter
 */
public interface TodoEntryRepository {
    TodoEntry.Id generateId();

    List<TodoEntry> findAllEntries();

    @Nullable
    TodoEntry findWithId(TodoEntry.Id id);

    void insertEntry(TodoEntry todoEntry);

    void updateEntry(TodoEntry todoEntry);

    void deleteEntry(TodoEntry todoEntry);

    List<TodoEntry> findWithPriority(@Nullable Priority priority);
}
