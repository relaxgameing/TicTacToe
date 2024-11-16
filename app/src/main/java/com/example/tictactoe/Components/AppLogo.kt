package com.example.tictactoe.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun AppLogo() {
    Text(
        "TIC TAC TOE",
        modifier = Modifier.Companion
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        textAlign = TextAlign.Companion.Center,
        fontSize = 40.sp,
        fontFamily = kantiFontFamily

    )
}