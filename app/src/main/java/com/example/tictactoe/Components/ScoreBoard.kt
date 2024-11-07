package com.example.tictactoe.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.bungeFontFamily
import com.example.tictactoe.ui.theme.kantiFontFamily


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