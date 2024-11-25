package com.example.tictactoe.Server

import android.util.Log
import com.example.tictactoe.State.online.RoomStateModal
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.serialization.json.Json


class WebSocketClient(private  val host: String) {

    private val client = HttpClient(OkHttp){
        install(WebSockets){
            contentConverter= KotlinxWebsocketSerializationConverter(Json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }


    var session: WebSocketSession? = null

    suspend fun joinRoom(roomId: String, username: String): Unit {
        try {
             client.webSocket(
                host = host,
                method = HttpMethod.Get,
                path = "game/room/join/$roomId?username=$username",
                port = 3000
            ){
                 session = this
                 session!!.incoming
                     .consumeAsFlow()
                     .filterIsInstance<Frame.Text>()
                     .onStart {
                         Log.d("retro" , "connection established")
                     }.onEach {
                         val message = it.readText()
                         Log.d("retro" , message)
                         try {
                             val jsonMessage = """${message}"""
                            Log.d("retro" , Json.decodeFromString<RoomStateModal>(jsonMessage).toString())
                         }catch (e: Exception){
                             Log.d("retro" , Json.decodeFromString(message))
                         }


                     }.onCompletion {
                         session = null
                     }
                     .collect{
                         Log.d("retro", "collected")
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