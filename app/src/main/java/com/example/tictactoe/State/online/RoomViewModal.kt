package com.example.tictactoe.State.online


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RoomViewModal(private val roomRepository: RoomStateRepository): ViewModel() {
    private var _roomState = mutableStateOf(roomRepository.roomState)
    val roomState = _roomState

    fun updateRoomMode(mode: String){
          roomRepository.updateRoomsMode(mode)
        _roomState.value = roomRepository.roomState.copy()
    }



}