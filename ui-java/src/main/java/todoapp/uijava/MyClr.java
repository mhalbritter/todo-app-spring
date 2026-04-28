package todoapp.uijava;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        if (entries.isEmpty()) {
            this.todos.addEntry("Record JSpecify video");
        }
    }
}
