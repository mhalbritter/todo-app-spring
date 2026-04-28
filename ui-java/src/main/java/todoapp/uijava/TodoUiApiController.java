package todoapp.uijava;

import java.util.List;

import todoapp.domain.TodoEntry;
import todoapp.domain.Todos;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Moritz Halbritter
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
class TodoUiApiController {
    private final Todos todos;

    TodoUiApiController(Todos todos) {
        this.todos = todos;
    }

    @GetMapping({"", "/"})
    List<TodoEntryDto> findAll() {
        return this.todos.findAllEntries().stream().map(TodoEntryDto::fromDomain).toList();
    }

    @GetMapping({"/{id}", "/{id}/"})
    ResponseEntity<TodoEntryDto> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(TodoEntryDto.fromDomain(this.todos.findById(TodoEntry.Id.of(id))));
        } catch (Todos.TodoEntryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    record TodoEntryDto(long id, String title, String status) {
        static TodoEntryDto fromDomain(TodoEntry entry) {
            return new TodoEntryDto(entry.id().id(), entry.title(), entry.status().name());
        }
    }
}
