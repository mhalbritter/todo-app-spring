package todoapp.domain;

import java.util.List;

/**
 * @author Moritz Halbritter
 */
public class Todos {
    private final TodoEntryRepository repository;

    public Todos(TodoEntryRepository repository) {
        this.repository = repository;
    }

    List<TodoEntry> findAllEntries() {
        return this.repository.findAllEntries();
    }

    TodoEntry addEntry(String title) {
        TodoEntry.Id id = this.repository.generateId();
        TodoEntry entry = new TodoEntry(id, title, Status.WAITING, null, null, null, null);
        this.repository.insertEntry(entry);
        return entry;
    }

    TodoEntry startEntry(TodoEntry.Id id) {
        TodoEntry entry = this.repository.findWithId(id);
        if (entry == null) {
            throw new TodoEntryNotFoundException(id);
        }
        TodoEntry updatedEntry = entry.withStatus(Status.IN_PROGRESS);
        this.repository.updateEntry(updatedEntry);
        return updatedEntry;
    }

    TodoEntry completeEntry(TodoEntry.Id id) {
        TodoEntry entry = this.repository.findWithId(id);
        if (entry == null) {
            throw new TodoEntryNotFoundException(id);
        }
        TodoEntry updatedEntry = entry.withStatus(Status.COMPLETED);
        this.repository.updateEntry(updatedEntry);
        return updatedEntry;
    }

    public static class TodoEntryNotFoundException extends RuntimeException {
        TodoEntryNotFoundException(TodoEntry.Id id) {
            super("Todo entry with id %s not found".formatted(id));
        }
    }

}
