package todoapp.domain;

import java.time.LocalDateTime;

import org.jspecify.annotations.Nullable;

/**
 * @author Moritz Halbritter
 */
public record TodoEntry(
    Id id,
    String title,
    Status status,
    @Nullable String description,
    @Nullable String assignee,
    @Nullable Priority priority,
    @Nullable LocalDateTime dueDate
) {
    public TodoEntry withStatus(Status status) {
        return new TodoEntry(this.id, this.title, status, this.description, this.assignee, this.priority, this.dueDate);
    }

    public TodoEntry withTitle(String title) {
        return new TodoEntry(this.id, title, this.status, this.description, this.assignee, this.priority, this.dueDate);
    }

    public TodoEntry withDescription(@Nullable String description) {
        return new TodoEntry(this.id, this.title, this.status, description, this.assignee, this.priority, this.dueDate);
    }

    public TodoEntry withAssignee(@Nullable String assignee) {
        return new TodoEntry(this.id, this.title, this.status, this.description, assignee, this.priority, this.dueDate);
    }

    public TodoEntry withPriority(@Nullable Priority priority) {
        return new TodoEntry(this.id, this.title, this.status, this.description, this.assignee, priority, this.dueDate);
    }

    public record Id(long id) {
        public static Id of(long id) {
            return new Id(id);
        }
    }
}
