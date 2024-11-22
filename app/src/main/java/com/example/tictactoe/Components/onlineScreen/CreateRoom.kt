package com.example.tictactoe.Components.onlineScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import com.example.tictactoe.State.online.UserStateViewModal
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun CreateRoom(userState: UserStateViewModal, username: String?, modifier: Modifier = Modifier.Companion) {
    val roomToken =
        remember(userState.user.value) { mutableStateOf<String?>(userState.user.value.roomToken) }

    val clipboardManager = LocalClipboardManager.current
    val localContext = LocalContext.current

    if (roomToken.value != null) {
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Text(
                "Room Id: ${roomToken.value}",
                fontFamily = kantiFontFamily,
                fontSize = 20.sp,
            )

            TextButton(
                onClick = {
                    if (roomToken.value != null) {
                        clipboardManager.setText(AnnotatedString(roomToken.value.toString()))
                        Toast.makeText(
                            localContext,
                            "room id copied to clipboard",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
            ) {
                Icon(
                    painterResource(R.drawable.copy_icon),
                    contentDescription = "copy",
                    modifier = modifier.size(20.dp)
                )
            }

        }

    }

    if (roomToken.value == null) {


        Button(onClick = {
            if (username != null) {
                userState.createNewRoom(username)
            }
        }, modifier = Modifier.Companion.wrapContentWidth()) {
            Icon(Icons.Filled.Home, contentDescription = "room")
            Spacer(Modifier.Companion.width(10.dp))
            Text("create room", fontFamily = kantiFontFamily, fontSize = 20.sp)
        }
    } else {
        Button(onClick = {
            //join room
        }, modifier = Modifier.Companion.wrapContentWidth()) {
            Icon(Icons.Filled.Home, contentDescription = "join room")
            Spacer(Modifier.Companion.width(10.dp))
            Text("join room", fontFamily = kantiFontFamily, fontSize = 20.sp)
        }
    }

}