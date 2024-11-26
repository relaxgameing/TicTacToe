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
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.isActive
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class WebSocketClient(private val host: String) {

    private val client = HttpClient(OkHttp) {
        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }


    var session: WebSocketSession? = null

    suspend fun joinRoomAndSetUpSocket(
        roomId: String,
        username: String,
        onCollect: (RoomStateModal)->Unit,
        navigateToGameRoom: () -> Unit
    ) {

        try {

            client.webSocket(
                method = HttpMethod.Get,
                host = "10.0.2.2",
                port = 3000,
                path = "game/room/join/$roomId?username=$username",
                request = {

                }
            )
//            client.webSocket("wss://relaxgamein-tictactoeba-92-9rtyvkkzbnht.deno.dev/game/room/join/$roomId?username=$username")
             {

                session = this
                Log.d("retro" , "inside wsclient ${this.isActive}")
                session!!.incoming
                    .consumeAsFlow()
                    .filterIsInstance<Frame.Text>()
                    .mapNotNull { Json.decodeFromString<RoomStateModal>(it.readText()) }
                    .onStart {
                        Log.d("retro", "connection complete")
                        navigateToGameRoom()
                    }
                    .onEach {
                        Log.d("retro", it.toString())
                    }
                    .catch {
                        Log.d("retro", it.message.toString())
                    }
                    .collect {
                        onCollect(it)
                    }
            }
        }catch (e : Exception){
            Log.d("retro" , "websocket error occured ${e.message} ")
        }
}

suspend fun makeMove(move: Move) {
    session?.outgoing?.send(Frame.Text(Json.encodeToString(move)))
}

suspend fun close() {
    session?.close()
    session = null
}
}

interface WebSocketListener {
    fun onConnected()
    fun onMessage(message: String)
    fun onDisconnected()
}


//try {
//    client.webSocket(
//        host = host,
//        method = HttpMethod.Get,
//        path = "game/room/join/$roomId?username=$username",
//        port = 3000,
////                 request = {
////                     this.headers.set("Content-Type" , "")
////                 }
//    ) {
//        session = this
//        val roomState = session!!.incoming
//            .consumeAsFlow()
//            .filterIsInstance<Frame.Text>()
//            .onStart {
//                Log.d("retro", "connection established")
//                navigateToGameRoom()
//            }.onEach {
//                val message = it.readText()
//                Log.d("retro", "\"$message\"")
//                val roomState = Json.decodeFromString<RoomStateModal>(message)
//                try {
//                    Log.d("retro", roomState.toString())
//                } catch (e: Exception) {
//                    Log.d("retro", Json.decodeFromString(message))
//                }
//
//                emit(roomState)
//            }
//
//    }
//
//
//} catch (e: Exception) {
//    Log.d("retro", "WebSocket error: ${e.message}")
//}