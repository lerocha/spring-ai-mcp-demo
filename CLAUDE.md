# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot Kotlin application that implements an MCP (Model Context Protocol) server demo using Spring AI. The project provides weather information tools and resources that can be accessed by Claude Desktop and other MCP clients.

## Key Architecture Components

- **MCP Server**: Built using Spring AI's `spring-ai-starter-mcp-server-webmvc` starter
- **Tool Provider**: Weather-related tools are exposed via Spring AI's `@Tool` annotation
- **Transport**: Configured for STDIO transport (standard input/output) for Claude Desktop integration
- **Configuration**: Uses Spring Boot's configuration system with MCP-specific settings in `application.yml`

## Essential Commands

### Build and Run
```bash
# Clean and build the project
./gradlew clean build

# Run the application locally
./gradlew bootRun

# Run tests
./gradlew test

# Check all verification tasks
./gradlew check
```

### MCP Server Integration
The built JAR is located at: `build/libs/spring-ai-mcp-demo-0.0.1-SNAPSHOT.jar`

For Claude Desktop integration, the server runs via STDIO transport using Java command:
```bash
java -jar build/libs/spring-ai-mcp-demo-0.0.1-SNAPSHOT.jar
```

## Code Structure

### Core Classes
- `McpServerApplication.kt`: Main Spring Boot application with tool callback provider configuration
- `WeatherService.kt`: Service class containing `@Tool` annotated methods for weather operations
- `application.yml`: MCP server configuration including capabilities, transport settings, and logging

### Tool Implementation Pattern
Tools are implemented as Spring service methods annotated with `@Tool`:
```kotlin
@Tool(description = "Get the weather for a city")
fun getWeather(cityName: String): String
```

These are automatically registered via `MethodToolCallbackProvider` in the main application class.

### Configuration Notes
- Banner mode is disabled (`banner-mode: off`) for STDIO transport compatibility
- Console logging is disabled (`root: OFF`) to prevent interference with MCP communication
- Management endpoints are exposed for monitoring: health, info, mappings, beans, env

## Development Environment
- **Language**: Kotlin 1.9.25
- **Java Version**: 21 (configured via toolchain)
- **Spring Boot**: 3.5.4
- **Spring AI**: 1.0.1
- **Build Tool**: Gradle with Kotlin DSL