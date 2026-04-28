package todoapp.clikotlin

import org.springframework.stereotype.Component
import todoapp.domain.TodoEntry.Id
import todoapp.domain.Todos

/**
 * @author Moritz Halbritter
 */
@Component
class CatCommand(private val todos: Todos) : Command {
    override val name = "cat"

    override fun run(args: List<String>) {
        if (args.isEmpty()) {
            println("Usage: cat <id>")
            return
        }
        val id = args.get(0).toLong()
        val todo = try {
            this.todos.findById(Id.of(id))
        } catch (ex: Todos.TodoEntryNotFoundException) {
            println("Todo entry with id $id not found")
            return
        }
        println("Title: ${todo.title}")
        println("Status: ${todo.status}")
        if (todo.description != null) {
            println("Description: ${todo.description}")
        }
        if (todo.assignee != null) {
            println("Assignee: ${todo.assignee}")
        }
        if (todo.priority != null) {
            println("Priority: ${todo.priority}")
        }
        if (todo.dueDate != null) {
            println("Due date: ${todo.dueDate}")
        }
    }

}
