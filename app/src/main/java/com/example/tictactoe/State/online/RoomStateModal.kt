package com.example.tictactoe.State.online

import android.adservices.adid.AdId
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RoomStateModal (
    @SerializedName("roomToken"     ) var roomToken     : String?                      = null,
    @SerializedName("player1"       ) var player1       : String?                      = null,
    @SerializedName("player2"       ) var player2       : String?                      = null,
    @SerializedName("isPlayer1Turn" ) var isPlayer1Turn : Boolean?                     = null,
    @SerializedName("isBoardFull"   ) var isBoardFull   : Boolean?                     = null,
    @SerializedName("isRoundOver"   ) var isRoundOver   : Boolean?                     = null,
    @SerializedName("mode"          ) var mode          : String?                      = null,
    @SerializedName("stack"         ) var stack         : ArrayList<String>            = arrayListOf(),
    @SerializedName("scores"        ) var scores        : ArrayList<Int>               = arrayListOf(),
    @SerializedName("board"         ) var board         : ArrayList<ArrayList<String>> = arrayListOf(),
    @SerializedName("rowHash"       ) var rowHash       : ArrayList<ArrayList<Int>>    = arrayListOf(),
    @SerializedName("colHash"       ) var colHash       : ArrayList<ArrayList<Int>>    = arrayListOf()

)

class RoomStateRepository(mode: String = "classic"){

    private var _roomState  = RoomStateModal(mode= mode)
    val roomState = _roomState

    fun updateBoardState(i:Int ,j:Int , value:String): RoomStateModal{
        _roomState.board[i][j] = value
        return _roomState
    }

}