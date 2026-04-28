package todoapp.clikotlin

import org.springframework.stereotype.Component
import todoapp.domain.TodoEntry
import todoapp.domain.Todos

/**
 * @author Moritz Halbritter
 */
@Component
class AssigneeReportCommand(private val todos: Todos) : Command {
    override val name = "assignee-report"
    override fun run(args: List<String>) {
        val report = this.todos.createAssigneeReport()
        report.map.forEach { (assignee, entry) ->
            println(assignee ?: "No assignee")
            if (entry.waiting.isNotEmpty()) {
                println("  Waiting:")
                entry.waiting.forEach { entry ->
                    renderEntry(entry)
                }
            }
            if (entry.inProgress.isNotEmpty()) {
                println("  In Progress:")
                entry.inProgress.forEach { entry ->
                    renderEntry(entry)
                }
            }
            if (entry.completed.isNotEmpty()) {
                println("  Completed:")
                entry.completed.forEach { entry ->
                    renderEntry(entry)
                }
            }
        }
    }

    private fun renderEntry(entry1: TodoEntry) {
        println("    ${entry1.id.id} - ${entry1.title}")
    }
}


