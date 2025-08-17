# Spring AI MCP Demo

This is a Spring Boot Kotlin application that implements an MCP (Model Context Protocol) server demo using Spring AI. The project provides mortgage tools and resources that can be accessed by Claude Desktop and other MCP clients.

- [MCP Server Boot Starter](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html)
- [Build an MCP Server](https://modelcontextprotocol.io/quickstart/server)

## Build the MCP Server

```bash
./gradlew clean build
```

## Connecting to Claude Desktop

To register your local MCP server with Claude Desktop, you'll need to configure it in Claude Desktop's configuration file.

### Claude Desktop Configuration File

The configuration file location depends on your operating system:
- macOS: `~/Library/Application Support/Claude/claude_desktop_config.json`
- Windows: `%APPDATA%/Claude/claude_desktop_config.json`
- Linux: `~/.config/Claude/claude_desktop_config.json`

### Add the MCP Server

Add the following to the configuration file and restart Claude Desktop. Make sure to update the java path and application path.

```json
{
  "mcpServers": {
    "spring-ai-mcp-server-demo": {
      "command": "/usr/bin/java",
      "args": [
        "-jar",
        "/Users/luis/Developer/spring-ai-mcp-demo/build/libs/spring-ai-mcp-demo-0.0.1-SNAPSHOT.jar"
      ],
      "env": {
        "ANY_ENV_VARS": "if_needed"
      }
    }
  }
}
```

### Checking the Logs

```bash
# Claude Desktop Logs
tail -f ~/Library/Logs/Claude/mcp-server-spring-ai-mcp-server-demo.log
```

```bash
# MCP Server Logs
tail -f /tmp/logs/spring-ai-mcp-demo.log
```
