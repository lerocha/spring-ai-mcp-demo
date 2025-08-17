package com.github.lerocha.mcpserver

import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Service

@Service
class WeatherService {

    @Tool(description = "Get the weather for a city")
    fun getWeather(cityName: String): String {
        // TODO: implementation
        return "Sunny"
    }

}