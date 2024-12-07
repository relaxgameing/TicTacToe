package com.example.tictactoe.Screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.tictactoe.State.online.RoomStateModal
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun OnlineBoardScreen(onlineState: OnlineModeState, navigateTo: () -> Unit) {

    val roomState = onlineState.roomState.roomState.collectAsState()
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
            Scores(roomState)
            if (roomState.value.gameOver) {
                OnlineWinnerBanner(roomState, onlineState::exitRoom, navigateTo)
            } else

                OnlineBoard(roomState, onlineState::sendMove)

        }
    }
}

@Composable
fun Scores(state: State<RoomStateModal>) {
    Column(
        modifier = Modifier.Companion.fillMaxWidth(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {

        AppLogo()
        Text("Online ${state.value.mode} mode", fontFamily = kantiFontFamily, fontSize = 20.sp)

        if (state.value.player1 != null) {

            Row(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ScoreBoard(state.value.player1, state.value.scores[0])
                Spacer(modifier = Modifier.Companion.width(32.dp))
                ScoreBoard(state.value.player2, state.value.scores[1])
            }
        }
        OnlineCurrentChance(state.value.isPlayer1Turn, state.value.player1, state.value.player2)
    }
}

@Composable
fun OnlineCurrentChance(isPlayer1Turn: Boolean, player1: String?, player2: String?) {
    if (player1 == null || player2 == null) {
        Text(
            "Waiting for other player",
            fontFamily = kantiFontFamily,
            fontSize = 20.sp
        )
    } else

        Text(
            " ${if (isPlayer1Turn) player1 else player2}  Turn",
            fontFamily = kantiFontFamily,
            fontSize = 20.sp
        )
}

@Composable
fun ScoreBoard(name: String?, score: Int, modifier: Modifier = Modifier.Companion) {
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Text(
            if (name == null) "Opponent" else name,
            fontSize = 24.sp,
            modifier = Modifier.Companion.padding(bottom = 10.dp),
            fontFamily = kantiFontFamily
        )

        Text("$score", fontSize = 32.sp, fontFamily = kantiFontFamily)
    }
}

@Composable
fun OnlineWinnerBanner(
    state: State<RoomStateModal>,
    exitRoom: (() -> Unit) -> Unit,
    navigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    val winner = remember {
        if (state.value.scores[0] == 3)
            state.value.player1
        else
            state.value.player2
    }
    Column(
        modifier = Modifier.size(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Player $winner won", fontFamily = kantiFontFamily, softWrap = true,
            letterSpacing = 3.sp, fontSize = 25.sp
        )


        Spacer(modifier = Modifier.height(15.dp))

        TextButton(
            onClick = {
                exitRoom(navigateTo)
                Log.d("debug", "button clicked")
            },
        ) {
            Icon(
                Icons.AutoMirrored.Default.ExitToApp,
                contentDescription = "refresh",
                tint = Color.White
            )
        }
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