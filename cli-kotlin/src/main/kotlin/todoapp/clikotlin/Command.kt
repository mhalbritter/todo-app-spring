package todoapp.clikotlin

/**
 * @author Moritz Halbritter
 */
interface Command {
    val name: String

    fun run(args: List<String>)
}
