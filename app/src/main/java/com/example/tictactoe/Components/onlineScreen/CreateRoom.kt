package com.example.tictactoe.Components.onlineScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import com.example.tictactoe.State.online.UserStateModal
import com.example.tictactoe.State.online.UserStateViewModal
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.Black
import com.example.tictactoe.ui.theme.BorderColor
import com.example.tictactoe.ui.theme.kantiFontFamily
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CreateRoom(
    userState: StateFlow<UserStateModal>,
    createNewRoom: (String  , String)-> Unit,
    joinRoom: ( String? ,  ()-> Unit ) -> Unit,
    navigateToRoom: ()-> Unit,
    modifier: Modifier = Modifier.Companion
) {
    val user = userState.collectAsState()
    val roomToken =
        remember(user.value) { mutableStateOf<String?>(user.value.roomToken) }

    val clipboardManager = LocalClipboardManager.current
    val localContext = LocalContext.current

    val roomId = rememberSaveable { mutableStateOf<String>("") }

    OutlinedTextField(
        if (roomToken.value.isNullOrBlank()) roomId.value else roomToken.value!!,
        onValueChange = { roomId.value = it },
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
            Text("Room ID:", fontFamily = kantiFontFamily)
        },
        trailingIcon = {
            if (false) {
                Icon(Icons.Filled.CheckCircle, contentDescription = "", tint = BackGroundColor)
            } else {
                Icon(Icons.Sharp.Create, contentDescription = "enter username")
            }
        },
        enabled = !user.value.isValidRoomToken
    )

    Spacer(modifier.height(20.dp))



    if (roomToken.value == null &&  roomId.value.length != 36) {

        Button(onClick = {
            Log.d("retro" , user.value.username.toString() )
            if (user.value.isValidUsername && !user.value.username.isNullOrBlank()) {
                createNewRoom(user.value.username!! , user.value.mode)
            }
        }, modifier = Modifier.Companion.wrapContentWidth()) {
            Icon(Icons.Filled.Home, contentDescription = "room")
            Spacer(Modifier.Companion.width(10.dp))
            Text("create room", fontFamily = kantiFontFamily, fontSize = 20.sp)
        }
    } else {

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
            Text("copy room id:" , fontFamily = kantiFontFamily)
            Spacer(modifier.width(10.dp))
            Icon(
                painterResource(R.drawable.copy_icon),
                contentDescription = "copy",
                modifier = modifier.size(20.dp)
            )
        }


        Button(onClick = {
            //join room
            joinRoom(roomId.value , navigateToRoom)
        }, modifier = Modifier.Companion.wrapContentWidth()) {
            Icon(Icons.Filled.Home, contentDescription = "join room")
            Spacer(Modifier.Companion.width(10.dp))
            Text("join room", fontFamily = kantiFontFamily, fontSize = 20.sp)
        }
    }

}