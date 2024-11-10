package com.example.tictactoe



import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.tictactoe.Components.Board
import com.example.tictactoe.Components.Header
import com.example.tictactoe.Components.WinningScreen
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.ui.theme.kantiFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameViewModel: GameViewModal = GameViewModal(GameStateRepository())
        val boardViewModal: BoardViewModal = BoardViewModal(BoardStateRepository())
        setContent {
            TicTacToeTheme {
                GameScreen(gameViewModel , boardViewModal)
            }
        }
    }
}

@Composable
fun GameScreen(gameViewModal: GameViewModal , boardViewModal: BoardViewModal) {
    val game  = remember {  gameViewModal.gameState.value }
    val board = remember {  boardViewModal.boardState.value}

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGroundColor)
            .padding(PaddingValues(16.dp)),
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGroundColor)
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Log.d("debug" , "recompose ${board}" )
            Header(game.playerScores)

            if (!board.isRoundOver.value && !board.isBoardFull.value) {
                CurrentChance(board.isPlayer1Turn.value)
                Board(board.board , boardViewModal::updateBoardState  , gameViewModal::updateGameState)
            } else {
                WinningScreen(
                    board.roundWinner.intValue,
                    game.winner,
                    if (gameViewModal.checkWinner()) {
                        {
                            gameViewModal.initialGameState()
                            boardViewModal.resetRound()
                        }
                    } else boardViewModal::resetRound
                )
            }
        }
    }
}

@Composable
fun CurrentChance(isPlayer1Turn: Boolean) {
    Text(
        "Player  ${if (isPlayer1Turn) 1 else 2}  Turn",
        fontFamily = kantiFontFamily,
        fontSize = 20.sp
    )
}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    val gameViewModel: GameViewModal = GameViewModal( GameStateRepository() )
    val boardViewModal: BoardViewModal = BoardViewModal(BoardStateRepository())
    TicTacToeTheme {
        GameScreen(gameViewModel , boardViewModal)
    }
}