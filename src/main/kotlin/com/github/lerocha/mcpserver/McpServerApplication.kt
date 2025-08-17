package com.github.lerocha.mcpserver

import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class McpServerApplication {
    @Bean
    fun tools(mortgageService: MortgageService): ToolCallbackProvider {
        return MethodToolCallbackProvider.builder()
            .toolObjects(mortgageService)
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<McpServerApplication>(*args)
}
