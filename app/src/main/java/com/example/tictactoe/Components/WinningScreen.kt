package com.example.tictactoe.Components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.bungeFontFamily
import com.example.tictactoe.ui.theme.kantiFontFamily


@Composable
fun WinningScreen(winner: Int, finalWinner: Int, resetRound: () -> Unit) {
    Column(
        modifier = Modifier.size(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (finalWinner > 0) {
            Text("Player $finalWinner won", fontFamily = kantiFontFamily, softWrap = true ,
                letterSpacing = 3.sp , fontSize = 25.sp )
        }
        else
            Text(if (winner > 0) "player $winner won the round" else "Round Draw", fontFamily = kantiFontFamily)
        Spacer(modifier = Modifier.height(15.dp))


        TextButton(
            onClick = {
                resetRound()
                Log.d("debug",  "button clicked")
              },
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = "refresh", tint = Color.White)
        }
    }

}