package todoapp.uijava;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import todoapp.domain.Priority;
import todoapp.domain.TodoEntry;
import todoapp.domain.Todos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Moritz Halbritter
 */
@Component
class MyClr implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyClr.class);

    private final Todos todos;

    MyClr(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void run(String... args) {
        insertEntries();
        listEntries();
    }

    private void listEntries() {
        List<TodoEntry> entries = this.todos.findAllEntries();
        LOGGER.info("Found {} entries.", entries.size());
        for (TodoEntry entry : entries) {
            LOGGER.info("{}", entry);
        }
    }

    private void insertEntries() {
        List<TodoEntry> entries = this.todos.findAllEntries();
        if (!entries.isEmpty()) {
            return;
        }
        TodoEntry trash = this.todos.addEntry("Take out trash");
        this.todos.updateEntry(trash.withAssignee("Alice").withPriority(Priority.MEDIUM));
        TodoEntry cleanBathroom = this.todos.addEntry("Clean bathroom");
        this.todos.updateEntry(cleanBathroom.withAssignee("Bob").withPriority(Priority.MEDIUM));
        TodoEntry lightBulb = this.todos.addEntry("Fix cellar light bulb");
        this.todos.updateEntry(lightBulb.withAssignee("Bob").withPriority(Priority.LOW));
        TodoEntry hornetNest = this.todos.addEntry("Remove hornet nest");
        this.todos.updateEntry(hornetNest.withAssignee("Charlie"));
    }
}
