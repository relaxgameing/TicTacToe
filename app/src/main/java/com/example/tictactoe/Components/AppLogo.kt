package com.example.tictactoe.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.kantiFontFamily
import com.example.tictactoe.R

@Composable
fun AppLogo() {
    Row (
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.cross),
            contentDescription = "cross logo",
            modifier = Modifier.size(60.dp)
        )
        Text(
            "TIC TAC TOE",
            modifier = Modifier
                .wrapContentHeight()
                .weight(1F),
            textAlign = TextAlign.Companion.Center,
            fontSize = 30.sp,
            fontFamily = kantiFontFamily,
        )
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "circle logo",
            modifier = Modifier.size(60.dp)
        )
    }
}