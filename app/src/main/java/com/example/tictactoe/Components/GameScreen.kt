package com.example.tictactoe.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.BoardStateRepository
import com.example.tictactoe.BoardViewModal
import com.example.tictactoe.GameStateRepository
import com.example.tictactoe.GameViewModal
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun GameScreen(gameViewModal: GameViewModal, boardViewModal: BoardViewModal, navigateTo: ()-> Unit) {
    val game = remember { gameViewModal.gameState }
    val board = remember { boardViewModal.boardState }

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
            Log.d("debug", "recompose ${board}")
            Header(game.value.playerScores)

            if (!board.value.isRoundOver.value && !board.value.isBoardFull.value) {
                CurrentChance(board.value.isPlayer1Turn.value)
                Board(
                    board.value.board,
                    boardViewModal::updateBoardState,
                    gameViewModal::updateGameState
                )
            } else {
                WinningScreen(
                    board.value.roundWinner.intValue,
                    game.value.winner,
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
    val gameViewModel: GameViewModal = GameViewModal(GameStateRepository())
    val boardViewModal: BoardViewModal = BoardViewModal(BoardStateRepository())
    TicTacToeTheme {
        GameScreen(gameViewModel, boardViewModal, {})
    }
}