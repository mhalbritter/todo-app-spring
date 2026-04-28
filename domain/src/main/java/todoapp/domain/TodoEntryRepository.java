package todoapp.domain;

import java.util.List;

/**
 * @author Moritz Halbritter
 */
public interface TodoEntryRepository {
    TodoEntry.Id generateId();

    List<TodoEntry> findAllEntries();

    TodoEntry findWithId(TodoEntry.Id id);

    void insertEntry(TodoEntry todoEntry);

    void updateEntry(TodoEntry todoEntry);

    void deleteEntry(TodoEntry todoEntry);
}
