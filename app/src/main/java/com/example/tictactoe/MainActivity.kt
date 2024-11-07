package com.example.tictactoe


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.tictactoe.ui.theme.bungeFontFamily
import com.example.tictactoe.ui.theme.honkFamily
import com.example.tictactoe.ui.theme.kantiFontFamily

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val game = GameState()

        setContent {
            TicTacToeTheme {
                GameScreen(game)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen(game: GameState) {

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
            Header(game)
            if (game.winner.value.isEmpty()) {
                CurrentChance(game.isPlayer1Turn.value)
                Board(game.matrix, game::updateGameState)
            } else
                WinningScreen(
                    game.winner.value,
                    game.finalWinner.intValue,
                    if(game.finalWinner.intValue != 0)game::backToInitialState else game::resetRound)
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
    TicTacToeTheme {
        GameScreen(game = GameState())
    }
}