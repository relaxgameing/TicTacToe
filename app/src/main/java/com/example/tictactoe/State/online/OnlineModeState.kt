package com.example.tictactoe.State.online

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.Server.Move
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OnlineModeState : ViewModel() {
    val userState = UserStateViewModal(UserStateRepository())
    val roomState = RoomViewModal(RoomStateRepository(), userState)


    fun joinRoom(roomId: String?, navigateToRoom: () -> Unit) {
        val curRoomId = if (!roomId.isNullOrBlank()) roomId
        else if (!userState.user.value.roomToken.isNullOrBlank()) userState.user.value.roomToken
        else null

        if (curRoomId == null || userState.user.value.username.isNullOrBlank()) return
        Log.d("retro", "joining room $curRoomId")
        viewModelScope.launch {
             userState.user.value.wsClient.joinRoomAndSetUpSocket(
                 curRoomId,
                userState.user.value.username!!,
                {
                    roomState.updateRoomState(it)
                },
                navigateToRoom
            )

        }
    }

    fun sendMove(i: Int, j: Int) {
        viewModelScope.launch {
            val move = Move(from = userState.user.value.username!!, arrayOf(i, j))
            Log.d("retro" , move.toString())
            userState.user.value.wsClient.makeMove(move)
        }
    }

    fun exitRoom(navigateTo:()-> Unit){
        viewModelScope.launch{
            Log.d("retro" , "logging out of room")
            userState.user.value.wsClient.close()
            userState.reset()
            roomState.reset()
            navigateTo()
        }
    }
}