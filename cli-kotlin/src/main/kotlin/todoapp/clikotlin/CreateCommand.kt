package todoapp.clikotlin

import org.springframework.stereotype.Component
import todoapp.domain.Todos

/**
 * @author Moritz Halbritter
 */
@Component
class CreateCommand(private val todos: Todos) : Command {
    override val name = "create"
    override fun run(args: List<String>) {
        if (args.isEmpty()) {
            println("Usage: create <title>")
            return
        }
        val title = args.joinToString(" ")
        val todo = this.todos.addEntry(title)
        println("Created ${todo.id.id} - ${todo.title}")
    }
}
