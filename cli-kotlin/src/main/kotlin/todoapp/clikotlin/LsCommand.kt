package todoapp.clikotlin

import org.springframework.stereotype.Component
import todoapp.domain.Todos

/**
 * @author Moritz Halbritter
 */
@Component
class LsCommand(private val todos: Todos) : Command {
    override val name = "ls"

    override fun run(args: List<String>) {
        this.todos.findAllEntries().forEach { entry ->
            print("${entry.id.id} - ${entry.title}")
            if (entry.assignee != null) {
                println(" (assigned to ${entry.assignee})")
            } else {
                println()
            }
        }
    }
}
