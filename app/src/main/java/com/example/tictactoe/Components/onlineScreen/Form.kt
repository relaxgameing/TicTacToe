package com.example.tictactoe.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tictactoe.Components.onlineScreen.CreateRoom
import com.example.tictactoe.Components.onlineScreen.EnterUsername
import com.example.tictactoe.State.online.OnlineModeState
import com.example.tictactoe.State.online.RoomViewModal
import com.example.tictactoe.State.online.UserStateViewModal

@Composable
fun Form(
    onlineState: OnlineModeState,
    navigateToRoom:()->Unit,
    modifier: Modifier = Modifier.Companion
) {

    val userState = remember { onlineState.userState }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {


        EnterUsername(
            userState.user,
            onlineState.userState::checkUsername
        )

        Spacer(Modifier.Companion.height(40.dp))

        SelectMode(userState.user , updateRoomMode = userState::updateRoomMode)

        Spacer(Modifier.Companion.height(60.dp))

        CreateRoom(userState.user , userState::createNewRoom , onlineState::joinRoom , navigateToRoom)

    }
}