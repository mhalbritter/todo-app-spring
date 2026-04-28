package todoapp.domain;

import java.time.LocalDateTime;

/**
 * @author Moritz Halbritter
 */
public record TodoEntry(
    Id id,
    String title,
    Status status,
    String description,
    String assignee,
    Priority priority,
    LocalDateTime dueDate
) {
    public TodoEntry withStatus(Status status) {
        return new TodoEntry(this.id, this.title, status, this.description, this.assignee, this.priority, this.dueDate);
    }

    public TodoEntry withTitle(String title) {
        return new TodoEntry(this.id, title, this.status, this.description, this.assignee, this.priority, this.dueDate);
    }

    public TodoEntry withDescription(String description) {
        return new TodoEntry(this.id, this.title, this.status, description, this.assignee, this.priority, this.dueDate);
    }

    public TodoEntry withAssignee(String assignee) {
        return new TodoEntry(this.id, this.title, this.status, this.description, assignee, this.priority, this.dueDate);
    }

    public TodoEntry withPriority(Priority priority) {
        return new TodoEntry(this.id, this.title, this.status, this.description, this.assignee, priority, this.dueDate);
    }

    public record Id(long id) {
        public static Id of(long id) {
            return new Id(id);
        }
    }
}
