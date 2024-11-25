package com.example.tictactoe.State.online

import com.example.tictactoe.Server.WebSocketClient

data class UserStateModal (
    var username: String?= null,
    var roomToken:String? = null,
    var isValidUsername: Boolean = false,
    var isValidRoomToken: Boolean = false,
    var mode: String ="classic" ,
    var wsClient: WebSocketClient = WebSocketClient("10.0.2.2")
)



class UserStateRepository{
    private  var _userState = UserStateModal()
    val userState = _userState

    fun updateUsername(username: String? ): UserStateModal{
        _userState.username = username
        this.setIsValidUsername(true)
        return _userState
    }

    fun  updateRoomToken(roomToken: String?): UserStateModal{
        _userState.roomToken= roomToken
        this.setIsValidRoomToken(true)
        return _userState
    }

    fun setIsValidUsername(valid: Boolean): UserStateModal{
        _userState.isValidUsername = valid
        return _userState
    }

    fun setIsValidRoomToken(valid: Boolean): UserStateModal{
        _userState.isValidRoomToken = valid
        return _userState
    }

    fun updateRoomMode(mode: String): UserStateModal{
        _userState.mode = mode
        return _userState
    }

}