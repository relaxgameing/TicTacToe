package com.example.tictactoe.State.online

import android.adservices.adid.AdId
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlin.arrayOf

@Serializable
data class RoomStateModal (
    @SerializedName("roomToken"     ) var roomToken     : String?                      = null,
    @SerializedName("player1"       ) var player1       : String?                      = null,
    @SerializedName("player2"       ) var player2       : String?                      = null,
    @SerializedName("isPlayer1Turn" ) var isPlayer1Turn : Boolean                      = true,
    @SerializedName("isBoardFull"   ) var isBoardFull   : Boolean                      = false,
    @SerializedName("isRoundOver"   ) var isRoundOver   : Boolean                      = false,
    @SerializedName("gameOver"      ) var gameOver      : Boolean                      = false,
    @SerializedName("mode"          ) var mode          : String                       = "classic",
    @SerializedName("stack"         ) var stack         : Array<Array<Int>>            = arrayOf(),
    @SerializedName("scores"        ) var scores        : ArrayList<Int>               = arrayListOf(0 , 0),
    @SerializedName("board"         ) var board         : Array<Array<String>>         = arrayOf(arrayOf("" ,"" ,"") , arrayOf("" ,"" ,"") , arrayOf("" ,"" ,"")),
    @SerializedName("rowHash"       ) var rowHash       : Array<Array<Int>>            = arrayOf(arrayOf(0 , 0), arrayOf(0 , 0) , arrayOf(0 , 0)),
    @SerializedName("colHash"       ) var colHash       : Array<Array<Int>>            = arrayOf(arrayOf(0 , 0) , arrayOf(0 , 0) ,arrayOf(0 , 0))

)

class RoomStateRepository(mode: String = "classic"){

    private var _roomState  = RoomStateModal(mode= mode)
    val roomState = _roomState

    fun updateRoomState(room: RoomStateModal): RoomStateModal{
        _roomState = room.copy()
        return _roomState
    }

    fun reset(): RoomStateModal{
        _roomState = RoomStateModal(mode = "classic")
        return _roomState
    }

}