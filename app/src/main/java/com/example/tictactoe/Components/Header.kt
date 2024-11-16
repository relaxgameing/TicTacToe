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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.GameState
import com.example.tictactoe.ui.theme.bungeFontFamily
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun Header(playerScores : SnapshotStateList<Int>) {
    Column(
        modifier = Modifier.Companion.fillMaxWidth(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {

        AppLogo()

        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ScoreBoard("Player 1",playerScores[0] )
            Spacer(modifier = Modifier.Companion.width(32.dp))
            ScoreBoard("Player 2", playerScores[1])
        }
    }
}