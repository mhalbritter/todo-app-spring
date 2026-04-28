package todoapp.clikotlin

import org.springframework.stereotype.Component

/**
 * @author Moritz Halbritter
 */
@Component
class HelpCommand(private val commands: List<Command>) : Command {
    override val name = "help"

    override fun run(args: List<String>) {
        println("Available commands:")
        this.commands.forEach { command ->
            println("  " + command.name)
        }
    }
}
