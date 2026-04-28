package todoapp.domain;

import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

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
    TodoEntry withStatus(Status status) {
        return new TodoEntry(this.id, this.title, status, this.description, this.assignee, this.priority, this.dueDate);
    }

    public record Id(long id) {
        public static Id of(long id) {
            return new Id(id);
        }
    }
}
