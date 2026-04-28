package todoapp.clikotlin

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * @author Moritz Halbritter
 */
@Component
class CLR(private val commands: List<Command>) : CommandLineRunner {
    override fun run(vararg args: String) {
        var line = readInput()
        while (line != null) {
            parseLine(line.trim())
            line = readInput()
        }
    }

    private fun readInput(): String? {
        print("> ")
        return readlnOrNull()
    }

    private fun parseLine(line: String) {
        if (line.isEmpty()) return

        val parts = line.split(" ")
        val commandName = parts.get(0)
        val command = commands.filter { it.name == commandName }.getOrNull(0)
        if (command == null) {
            println("Unknown command: $commandName - use help to see all available commands")
            return
        }
        command.run(parts.subList(1, parts.size))
    }
}
