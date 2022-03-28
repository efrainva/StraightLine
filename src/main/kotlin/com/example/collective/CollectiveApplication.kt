package com.example.collective

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CollectiveApplication

fun main(args: Array<String>) {
	runApplication<CollectiveApplication>(*args)
}
