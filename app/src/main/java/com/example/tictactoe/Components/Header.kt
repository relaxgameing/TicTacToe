package com.example.tictactoe.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.GameState
import com.example.tictactoe.ui.theme.bungeFontFamily
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun Header(gameState: GameState) {
    Column(
        modifier = Modifier.Companion.fillMaxWidth(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {

        Text("Tic Tac Toe", fontSize = 40.sp, fontFamily = kantiFontFamily)

        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ScoreBoard("Player 1",gameState.player1Score.intValue )
            Spacer(modifier = Modifier.Companion.width(32.dp))
            ScoreBoard("Player 2", gameState.player2Score.intValue)
        }
    }
}