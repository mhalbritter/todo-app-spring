package todoapp.uijava;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import todoapp.domain.Priority;
import todoapp.domain.Status;
import todoapp.domain.TodoEntry;
import todoapp.domain.Todos;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Moritz Halbritter
 */
@Controller
@RequestMapping(path = "", produces = MediaType.TEXT_HTML_VALUE)
class TodoUiController {
    private final Todos todos;

    TodoUiController(Todos todos) {
        this.todos = todos;
    }

    @GetMapping
    public ModelAndView index() {
        List<TodoEntry> allEntries = this.todos.findAllEntries();
        return new ModelAndView("index", Map.of("model", new IndexModel(
            filterWaiting(allEntries, Priority.HIGH),
            filterWaiting(allEntries, Priority.MEDIUM),
            filterWaiting(allEntries, Priority.LOW),
            filterWaiting(allEntries, null),
            filter(allEntries, Status.IN_PROGRESS),
            filter(allEntries, Status.COMPLETED)
        )));
    }

    @GetMapping("add")
    public ModelAndView add() {
        return new ModelAndView("add");
    }

    @PostMapping("add")
    public RedirectView add(@RequestParam String title) {
        this.todos.addEntry(title);
        return new RedirectView("/");
    }

    @GetMapping("/assignee-report")
    public ModelAndView assigneeReport() {
        Todos.AssigneeReport report = this.todos.createAssigneeReport();
        return new ModelAndView("assignee-report", Map.of("model", new AssigneeReportModel(report)));
    }

    private List<TodoEntry> filter(List<TodoEntry> entries, Status status) {
        return entries.stream().filter((e) -> e.status() == status).toList();
    }

    private List<TodoEntry> filterWaiting(List<TodoEntry> entries, @Nullable Priority priority) {
        return entries.stream().filter((e) -> e.status() == Status.WAITING && e.priority() == priority).toList();
    }

    @GetMapping("{id}")
    public ModelAndView details(@PathVariable long id) {
        TodoEntry entry = this.todos.findById(TodoEntry.Id.of(id));
        return new ModelAndView("details", Map.of("model", new DetailsModel(entry)));
    }

    @PostMapping("{id}")
    public RedirectView update(
        @PathVariable long id,
        @RequestParam String title,
        @RequestParam String status,
        @RequestParam @Nullable String description,
        @RequestParam @Nullable String assignee,
        @RequestParam @Nullable String priority
    ) {
        TodoEntry entry = this.todos.findById(TodoEntry.Id.of(id));
        entry = entry.withTitle(title);
        entry = entry.withStatus(Status.valueOf(status));
        entry = entry.withDescription(StringUtils.hasText(description) ? description : null);
        entry = entry.withAssignee(StringUtils.hasText(assignee) ? assignee : null);
        entry = entry.withPriority(StringUtils.hasText(priority) ? Priority.valueOf(priority) : null);
        this.todos.updateEntry(entry);
        return new RedirectView("/");
    }

    public record IndexModel(List<TodoEntry> high, List<TodoEntry> medium, List<TodoEntry> low, List<TodoEntry> others, List<TodoEntry> inProgress, List<TodoEntry> completed) {
    }

    public record DetailsModel(TodoEntry entry) {
    }

    public record AssigneeReportModel(Todos.AssigneeReport report) {
    }
}
