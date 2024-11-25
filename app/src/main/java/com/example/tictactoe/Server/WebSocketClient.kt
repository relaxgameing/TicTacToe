package com.example.tictactoe.Server

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText

class WebSocketClient(private  val url: String) {

    private val client = HttpClient{
        install(WebSockets)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun joinRoom(roomId: String, username: String): Unit {
        try {
            client.webSocket(
                method = HttpMethod.Get,
                host = "10.0.2.2",
                port = 3000,
                path = "/game/room/join/$roomId?username=$username" // adjust this path based on your server endpoint
            ) {
                // Connection successful
                Log.d("retro" , "WebSocket connection established")

                // Listen to incoming messages
                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val message = frame.readText()
                            Log.d("retro" , "Received: $message")
                        }
                        else -> Log.d("retro" , "Received other frame: $frame")
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("retro" , "WebSocket error: ${e.message}")
            throw e
        }
    }
}

interface WebSocketListener {
    fun onConnected()
    fun onMessage(message: String)
    fun onDisconnected()
}