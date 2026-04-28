package todoapp.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Moritz Halbritter
 */
public class Todos {
    private final TodoEntryRepository repository;

    public Todos(TodoEntryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TodoEntry> findAllEntries() {
        return this.repository.findAllEntries();
    }

    @Transactional
    public TodoEntry addEntry(String title) {
        TodoEntry.Id id = this.repository.generateId();
        TodoEntry entry = new TodoEntry(id, title, Status.WAITING, null, null, null, null);
        this.repository.insertEntry(entry);
        return entry;
    }

    @Transactional
    public TodoEntry startEntry(TodoEntry.Id id) {
        TodoEntry entry = this.repository.findWithId(id);
        if (entry == null) {
            throw new TodoEntryNotFoundException(id);
        }
        TodoEntry updatedEntry = entry.withStatus(Status.IN_PROGRESS);
        this.repository.updateEntry(updatedEntry);
        return updatedEntry;
    }

    @Transactional
    public TodoEntry completeEntry(TodoEntry.Id id) {
        TodoEntry entry = this.repository.findWithId(id);
        if (entry == null) {
            throw new TodoEntryNotFoundException(id);
        }
        TodoEntry updatedEntry = entry.withStatus(Status.COMPLETED);
        this.repository.updateEntry(updatedEntry);
        return updatedEntry;
    }

    @Transactional(readOnly = true)
    public TodoEntry findById(TodoEntry.Id id) {
        TodoEntry entry = this.repository.findWithId(id);
        if (entry == null) {
            throw new TodoEntryNotFoundException(id);
        }
        return entry;
    }

    @Transactional
    public TodoEntry updateEntry(TodoEntry entry) {
        this.repository.updateEntry(entry);
        return entry;
    }

    @Transactional(readOnly = true)
    public List<TodoEntry> findEntriesWithPriority(Priority priority) {
        return this.repository.findWithPriority(priority);
    }

    @Transactional(readOnly = true)
    public AssigneeReport createAssigneeReport() {
        Map<String, AssigneeReport.AssigneeEntry> map = new LinkedHashMap<>();
        List<TodoEntry> allEntries = this.repository.findAllEntries();
        map.put(null, AssigneeReport.AssigneeEntry.empty());
        for (TodoEntry entry : allEntries) {
            AssigneeReport.AssigneeEntry assigneeEntry = map.computeIfAbsent(entry.assignee(), (_) -> AssigneeReport.AssigneeEntry.empty());
            List<TodoEntry> listToAdd = switch (entry.status()) {
                case WAITING -> assigneeEntry.waiting();
                case IN_PROGRESS -> assigneeEntry.inProgress();
                case COMPLETED -> assigneeEntry.completed();
            };
            listToAdd.add(entry);
        }
        return new AssigneeReport(map);
    }

    @Transactional(readOnly = true)
    public PriorityReport createPriorityReport() {
        Map<String, List<TodoEntry>> map = new LinkedHashMap<>();
        List<TodoEntry> noPriority = new ArrayList<>();
        List<TodoEntry> allEntriesWithPriorities = this.repository.findAllEntries();
        for (TodoEntry entry : allEntriesWithPriorities) {
            if (hasPriority(entry)) {
                map.computeIfAbsent(entry.priority().name(), (_) -> new ArrayList<>()).add(entry);
            } else {
                noPriority.add(entry);
            }
        }
        return new PriorityReport(map, noPriority);
    }

    private boolean hasPriority(TodoEntry entry) {
        return entry.priority() != null;
    }

    /**
     * Assignee report.
     *
     * @param map maps from assignee to assignee entries. Key can be {@code null}!
     */
    public record AssigneeReport(Map<String, AssigneeEntry> map) {
        public record AssigneeEntry(List<TodoEntry> completed, List<TodoEntry> inProgress, List<TodoEntry> waiting) {
            static AssigneeEntry empty() {
                return new AssigneeEntry(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            }
        }
    }

    public record PriorityReport(Map<String, List<TodoEntry>> map, List<TodoEntry> noPriority) {
    }

    public static class TodoEntryNotFoundException extends RuntimeException {
        TodoEntryNotFoundException(TodoEntry.Id id) {
            super("Todo entry with id %s not found".formatted(id));
        }
    }

}
