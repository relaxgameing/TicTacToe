package com.example.tictactoe.Server

import android.R
import com.example.tictactoe.State.online.RoomStateModal
import com.google.gson.annotations.SerializedName

data class TestResponse (
    @SerializedName("rooms" ) var rooms : ArrayList<Rooms> = arrayListOf()
)

data class NewRoomResponse(
    @SerializedName("payload") val roomId: String?,
    @SerializedName("message") val message: String?
)

data class PayloadResponse(
    @SerializedName("payload") val isValidUsername: Boolean
)

data class Rooms (

    @SerializedName("key"          ) var key          : ArrayList<String> = arrayListOf(),
    @SerializedName("value"        ) var value        : RoomStateModal?   = RoomStateModal(),
    @SerializedName("versionstamp" ) var versionstamp : String?           = null

)