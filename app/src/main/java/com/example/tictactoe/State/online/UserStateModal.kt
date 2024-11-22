package com.example.tictactoe.State.online

data class UserStateModal (
    var username: String?= null,
    var roomToken:String? = null,
)

class UserStateRepository{
    private  var _userState = UserStateModal()
    val userState = _userState

    fun updateUsername(username: String? ): UserStateModal{
        _userState.username = username
        return _userState
    }

    fun  updateRoomToken(roomToken: String?): UserStateModal{
        _userState.roomToken= roomToken
        return _userState
    }

}