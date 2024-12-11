package com.example.tictactoe.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.ButtonColor
import com.example.tictactoe.ui.theme.kantiFontFamily


@Composable
fun FuntionalButton(
    textValue: String = "text",
    fontSize: TextUnit = 20.sp,
    textColor: Color = Color.Companion.Black,
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick, modifier = modifier
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
            .border(
                2.dp,
                Color.Companion.Black,
                androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
            )
            .background(ButtonColor)
    ) {
        Text(
            textValue,
            fontFamily = kantiFontFamily,
            fontSize = fontSize,
            color = textColor,
        )
        Spacer(Modifier.Companion.width(10.dp))
        icon()
    }
}

//reference
//              TextButton(
//                onClick = {
//                    exitRoom(navigateTo)
//                    Log.d("debug", "button clicked")
//                }, modifier = Modifier.Companion
//
//                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
//                    .border(
//                        2.dp,
//                        Color.Companion.Black,
//                        androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
//                    )
//                    .background(ButtonColor)
//            ) {
//                Text(
//                    "Exit",
//                    fontFamily = kantiFontFamily,
//                    fontSize = 20.sp,
//                    color = Color.Companion.Black
//                )
//                Spacer(Modifier.Companion.width(10.dp))
//                Icon(
//                    Icons.AutoMirrored.Default.ExitToApp,
//                    contentDescription = "refresh",
//                    tint = Color.Companion.White
//                )
//            }