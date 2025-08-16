package com.github.lerocha.mcpserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class McpserverApplication

fun main(args: Array<String>) {
	runApplication<McpserverApplication>(*args)
}
