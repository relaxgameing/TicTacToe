package com.example.tictactoe.Components.onlineScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.sharp.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.State.online.UserStateModal
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.Black
import com.example.tictactoe.ui.theme.BorderColor
import com.example.tictactoe.ui.theme.ButtonColor
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun EnterUsername(
    user: State<UserStateModal>,
    checkUserName: (String) -> Unit,
    isRoomCreated: Boolean,
    modifier: Modifier = Modifier.Companion
) {
    val username = remember {
        mutableStateOf<String>("")
    }

    val isValid = remember(user.value) { mutableStateOf(user.value.username != null) }

    OutlinedTextField(
        username.value, onValueChange = { username.value = it },
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = BorderColor,
            focusedContainerColor = BorderColor,
            unfocusedBorderColor = Black,
            focusedBorderColor = Black,
            focusedLabelColor = Black,
        ),
        shape = RoundedCornerShape(15.dp),
        label = {
            Text("username", fontFamily = kantiFontFamily)
        },
        trailingIcon = {
            if (isValid.value) {
                Icon(Icons.Filled.CheckCircle, contentDescription = "", tint = BackGroundColor)
            } else {
                Icon(Icons.Sharp.Create, contentDescription = "enter username")
            }
        },
        enabled = !isRoomCreated
    )
    Spacer(Modifier.Companion.height(20.dp))

    TextButton(
        onClick = {
            Log.d("retro", username.value)
//        user.test()
            if (username.value.isNotBlank())
                checkUserName(username.value)
            Log.d("retro", isValid.value.toString())
        },
        modifier = Modifier.Companion
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(15.dp))
            .background(ButtonColor)
            .border(
                BorderStroke(1.dp, Black),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp)
            )

    ) {
        Text("Check Username", fontFamily = kantiFontFamily, color = Black, fontSize = 15.sp)
    }
}