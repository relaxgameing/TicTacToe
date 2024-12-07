package com.example.tictactoe.State.online


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoomViewModal(
    private val roomRepository: RoomStateRepository,
    private val userViewModal: UserStateViewModal
) : ViewModel() {
    private var _roomState = MutableStateFlow<RoomStateModal>(roomRepository.roomState)
    val roomState: StateFlow<RoomStateModal> = _roomState.asStateFlow()

    fun updateRoomState(room: RoomStateModal){
        viewModelScope.launch{
            _roomState.emit(roomRepository.updateRoomState(room).copy())
        }
    }

    fun reset(){
        viewModelScope.launch{
            _roomState.emit(roomRepository.reset().copy())
        }
    }


}