package com.example.tictactoe.State.online


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.Server.retroService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RoomViewModal(private val roomRepository: RoomStateRepository): ViewModel() {
    private var _roomState = mutableStateOf(roomRepository.roomState)
    val roomState = _roomState

    fun updateRoomMode(mode: String){
          roomRepository.updateRoomsMode(mode)
        _roomState.value = roomRepository.roomState.copy()
    }



}