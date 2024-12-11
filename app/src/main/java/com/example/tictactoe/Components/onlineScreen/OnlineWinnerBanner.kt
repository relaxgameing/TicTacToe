package com.example.tictactoe.Components.onlineScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.Components.FuntionalButton
import com.example.tictactoe.State.online.RoomStateModal
import com.example.tictactoe.ui.theme.ButtonColor
import com.example.tictactoe.ui.theme.kantiFontFamily

@Composable
fun OnlineWinnerBanner(
    state: State<RoomStateModal>,
    exitRoom: (() -> Unit) -> Unit,
    rematch: () -> Unit,
    navigateTo: () -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    val winner = remember {
        if (state.value.scores[0] == 3) state.value.player1
        else state.value.player2
    }

    AfterMatchOptions(winner, state, exitRoom, rematch, navigateTo, modifier)

}

@Composable
fun AfterMatchOptions(
    winner: String?,
    state: State<RoomStateModal>,
    exitRoom: (() -> Unit) -> Unit,
    rematch: () -> Unit,
    navigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.Companion.size(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {


        Text(
            "Player $winner won",
            fontFamily = kantiFontFamily,
            softWrap = true,
            letterSpacing = 3.sp,
            fontSize = 25.sp
        )

        if ((state.value.wantRematch[0] == true || state.value.wantRematch[1] == true)) {
            Spacer(Modifier.height(24.dp))
            Text(
                "Rematch Status",
                fontFamily = kantiFontFamily,
                fontSize = 24.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        if (state.value.player1 != null) state.value.player1!! else "opponent",
                        fontFamily = kantiFontFamily
                    )
                    when (state.value.wantRematch[0]) {
                        true -> {
                            Icon(
                                Icons.Outlined.CheckCircle,
                                contentDescription = "tick",
                                tint = Color.Green
                            )
                        }

                        null -> {
                            Text("waiting...", fontFamily = kantiFontFamily)
                        }

                        false -> {
                            Icon(
                                Icons.Outlined.Close, contentDescription = "cross", tint = Color.Red
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        if (state.value.player2 != null) state.value.player2!! else "opponent",
                        fontFamily = kantiFontFamily
                    )


                    when (state.value.wantRematch[1]) {
                        true -> {
                            Icon(
                                Icons.Outlined.CheckCircle,
                                contentDescription = "tick",
                                tint = Color.Green
                            )
                        }

                        null -> {
                            Text("waiting...", fontFamily = kantiFontFamily)
                        }

                        false -> {
                            Icon(
                                Icons.Outlined.Close, contentDescription = "cross", tint = Color.Red
                            )
                        }

                    }

                }
            }
            Spacer(Modifier.height(24.dp))
        }


        Spacer(modifier = Modifier.Companion.height(15.dp))

        Column(
            modifier = Modifier.Companion.fillMaxWidth(),
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            if ((state.value.player1 == null || state.value.player2 == null)) {
                Text(
                    "opponent left, rematch not possible",
                    fontFamily = kantiFontFamily,
                    fontSize = 20.sp,
                    color = Color.Companion.Black,
                    modifier = Modifier.Companion
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            2.dp,
                            Color.Companion.Black,
                            androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                        )
                        .background(ButtonColor)
                        .padding(8.dp),
                    textAlign = TextAlign.Companion.Center
                )
                Spacer(Modifier.Companion.width(10.dp))

            } else {
                FuntionalButton(onClick = {
                    rematch()
                    Log.d("debug", "rematching")
                }, textValue = "Rematch", icon = {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "rematch",
                        tint = Color.Companion.White
                    )
                })
            }

            Spacer(modifier.height(10.dp))

            FuntionalButton(onClick = {
                exitRoom(navigateTo)
                Log.d("debug", "button clicked")
            }, textValue = "Exit", icon = {
                Icon(
                    Icons.AutoMirrored.Default.ExitToApp,
                    contentDescription = "refresh",
                    tint = Color.Companion.White
                )
            })

        }

    }
}