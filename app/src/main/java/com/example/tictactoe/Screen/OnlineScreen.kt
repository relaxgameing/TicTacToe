package com.example.tictactoe.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.Components.AppLogo
import com.example.tictactoe.State.online.OnlineModeState
import com.example.tictactoe.State.online.RoomStateRepository
import com.example.tictactoe.State.online.RoomViewModal
import com.example.tictactoe.State.online.UserStateRepository
import com.example.tictactoe.State.online.UserStateViewModal
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.TicTacToeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnlineScreen(
    onlineState: OnlineModeState,
    navigateToRoom: ()-> Unit,
    navigateBack: (String) -> Unit
) {
    Scaffold(
        topBar = {
            IconButton(onClick = { navigateBack("home") }) {
                Icon(Icons.Sharp.Close, contentDescription = "go back")
            }
        },
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.Companion
                .background(BackGroundColor)
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            AppLogo()
            Spacer(Modifier.Companion.height(40.dp))
            Form(onlineState ,navigateToRoom)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnlineScreenPreview() {
    TicTacToeTheme {
        OnlineScreen(
            OnlineModeState(),{},
            {})
    }
}