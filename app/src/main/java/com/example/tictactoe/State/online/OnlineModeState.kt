package com.example.tictactoe.State.online

class OnlineModeState {
    val userState = UserStateViewModal(UserStateRepository())
    val roomState = RoomViewModal(RoomStateRepository())
}