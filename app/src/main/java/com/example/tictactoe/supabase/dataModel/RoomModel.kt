package com.example.tictactoe.supabase.dataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.Array
import kotlin.uuid.Uuid

@Serializable
data class RoomModel(
    val id: Int,                         // 'serial' becomes 'Int'
    @SerialName("room_token")val roomToken: String,               // 'text' becomes 'String'
    @SerialName("player1_connection")val player1Connection: String?,      // 'text' (nullable) becomes 'String?'
    @SerialName("player2_connection")val player2Connection: String?,      // 'text' (nullable) becomes 'String?'
    val board: Array<Array<String>> = Array(3){arrayOf("" , "" , "")},                  // 'jsonb' (nullable) can be represented as 'String?' (or JsonObject if using a library like Gson or Jackson)
    @SerialName("is_player1_turn")val isPlayer1Turn: Boolean? = true,  // 'boolean' (nullable, with default value) becomes 'Boolean?'
    @SerialName("row_hash") val rowHash: Array<Array<Int>> = Array(3){arrayOf(0 , 0)},                // 'jsonb' (nullable) can be represented as 'String?'
    @SerialName("col_hash") val colHash: Array<Array<Int>> = Array(3){arrayOf(0 , 0)},                // 'jsonb' (nullable) can be represented as 'String?'
    @SerialName("is_board_full")val isBoardFull: Boolean? = false,   // 'boolean' (nullable, with default value) becomes 'Boolean?'
    @SerialName("is_round_over")val isRoundOver: Boolean? = false,   // 'boolean' (nullable, with default value) becomes 'Boolean?'
    @SerialName("round_winner")val roundWinner: Int? = 0,           // 'integer' (nullable, with default value) becomes 'Int?'
    val mode: String? = "classic",       // 'text' (nullable, with default value) becomes 'String?'
    @SerialName("game_stack")val gameStack: Array<Array<Int>> = emptyArray<Array<Int>>(),              // 'jsonb' (nullable) can be represented as 'String?'
    @SerialName("player_scores")val playerScores: Array<Int> = emptyArray<Int>(),           // 'jsonb' (nullable) can be represented as 'String?'
    val winner: Int? = 0                 // 'integer' (nullable, with default value) becomes 'Int?'
)

@Serializable
data class Country(
    val id: Int,
    val name: String,
    val client_addr: String?
)