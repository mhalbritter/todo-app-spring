package todoapp.clikotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoAppCliKotlinApplication

fun main(args: Array<String>) {
    runApplication<TodoAppCliKotlinApplication>(*args)
}

