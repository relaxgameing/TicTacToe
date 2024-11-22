package com.example.tictactoe.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tictactoe.Components.onlineScreen.CreateRoom
import com.example.tictactoe.Components.onlineScreen.EnterUsername
import com.example.tictactoe.State.online.RoomViewModal
import com.example.tictactoe.State.online.UserStateViewModal

@Composable
fun Form(
    userState: UserStateViewModal,
    roomViewModal: RoomViewModal,
    modifier: Modifier = Modifier.Companion
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {


        EnterUsername(
            userState.user,
            userState::checkUsername,
            userState.user.value.roomToken != null
        )

        Spacer(Modifier.Companion.height(40.dp))

        SelectMode(roomViewModal)

        Spacer(Modifier.Companion.height(60.dp))


        CreateRoom(userState, userState.user.value.username)

    }
}