package com.example.tictactoe.Server

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets

class WebSocketClient(private  val url: String) {

    private val client = HttpClient{
        install(WebSockets)
    }

    fun joinRoom(roomId: String, username: String): Unit {

    }
}

interface WebSocketListener {
    fun onConnected()
    fun onMessage(message: String)
    fun onDisconnected()
}