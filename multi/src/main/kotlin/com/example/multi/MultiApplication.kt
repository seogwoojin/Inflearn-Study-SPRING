package com.example.multi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MultiApplication

fun main(args: Array<String>) {
	runApplication<MultiApplication>(*args)
}
