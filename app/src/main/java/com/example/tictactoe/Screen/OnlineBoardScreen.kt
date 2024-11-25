package com.example.tictactoe.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.Components.AppLogo
import com.example.tictactoe.Components.onlineScreen.OnlineBoard
import com.example.tictactoe.State.offline.BoardStateRepository
import com.example.tictactoe.State.offline.BoardViewModal
import com.example.tictactoe.State.offline.GameStateRepository
import com.example.tictactoe.State.offline.GameViewModal
import com.example.tictactoe.State.online.OnlineModeState
import com.example.tictactoe.State.online.RoomViewModal
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun OnlineBoardScreen(onlineState: OnlineModeState, navigateTo: ()-> Unit) {

    Scaffold(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(color = BackGroundColor)
            .padding(PaddingValues(16.dp)),
        topBar = {
            IconButton(onClick = { navigateTo() }) {
                Icon(Icons.Sharp.Close, contentDescription = "go back")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(BackGroundColor)
                .padding(padding),
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Scores(onlineState.roomState)

            OnlineBoard(onlineState.roomState , onlineState::sendMove)

        }
    }
}

@Composable
fun Scores(onlineState: RoomViewModal) {
    val state = onlineState.roomState.collectAsState()
    Column(
        modifier = Modifier.Companion.fillMaxWidth(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {

        AppLogo()
        Text("${state.value.mode} mode" , fontFamily = kantiFontFamily , fontSize = 20.sp)
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ScoreBoard("Player 1",state.value.scores[0] )
            Spacer(modifier = Modifier.Companion.width(32.dp))
            ScoreBoard("Player 2", state.value.scores[1])
        }
        OnlineCurrentChance(state.value.isPlayer1Turn == true)
    }
}

@Composable
fun OnlineCurrentChance(isPlayer1Turn: Boolean) {
    Text(
        "Player  ${if (isPlayer1Turn) 1 else 2}  Turn",
        fontFamily = kantiFontFamily,
        fontSize = 20.sp
    )
}

@Composable
fun ScoreBoard(name: String, score: Int, modifier: Modifier = Modifier.Companion) {
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Text(
            "$name ",
            fontSize = 32.sp,
            modifier = Modifier.Companion.padding(bottom = 10.dp),
            fontFamily = kantiFontFamily
        )

        Text("$score", fontSize = 32.sp, fontFamily = kantiFontFamily)
    }
}




@Preview(showBackground = true)
@Composable
fun OnlineBoardScreenPreview() {
    val gameViewModel: GameViewModal = GameViewModal(GameStateRepository())
    val boardViewModal: BoardViewModal = BoardViewModal(BoardStateRepository())
    TicTacToeTheme {
        OnlineBoardScreen(onlineState = OnlineModeState(), {})
    }
}