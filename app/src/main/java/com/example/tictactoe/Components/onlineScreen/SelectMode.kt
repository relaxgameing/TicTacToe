package com.example.tictactoe.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.State.online.UserStateModal
import com.example.tictactoe.ui.theme.Black
import com.example.tictactoe.ui.theme.BorderColor
import com.example.tictactoe.ui.theme.SelectedColor
import com.example.tictactoe.ui.theme.kantiFontFamily
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SelectMode(userState: StateFlow<UserStateModal>, updateRoomMode: (String) -> Unit, modifier: Modifier = Modifier.Companion) {

    val user = userState.collectAsState()
    val mode = remember (user.value){ mutableStateOf(user.value.mode) }

    Column(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        Text(
            text = "Select Mode:",
            fontFamily = kantiFontFamily,
            fontSize = 20.sp
        )
        Spacer(Modifier.Companion.height(10.dp))
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TextButton(
                onClick = {
                    updateRoomMode("classic")
                },
                modifier = Modifier.Companion
                    .clip(RoundedCornerShape(15.dp))
                    .background(if (mode.value == "classic") SelectedColor else BorderColor)
                    .border(
                        BorderStroke(1.dp, Black),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp)
                    )
                    .weight(1f),

                ) {
                Text("Classic", fontFamily = kantiFontFamily, color = Black, fontSize = 20.sp)
            }
            Spacer(Modifier.Companion.width(10.dp))

            TextButton(
                onClick = {
                    updateRoomMode("crazy")
                },
                modifier = Modifier.Companion
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(15.dp))
                    .background(if (mode.value == "crazy") SelectedColor else BorderColor)
                    .border(
                        BorderStroke(1.dp, Black),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp)
                    )
                    .weight(1f),

                ) {
                Text("crazy", fontFamily = kantiFontFamily, color = Black, fontSize = 20.sp)
            }
        }
    }
}