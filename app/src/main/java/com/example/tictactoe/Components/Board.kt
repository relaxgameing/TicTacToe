package com.example.tictactoe.Components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tictactoe.R
import com.example.tictactoe.ui.theme.BackGroundColor

@SuppressLint("MutableCollectionMutableState")
@Composable
fun Board(
    gameMatrix: SnapshotStateList<SnapshotStateList<String>>,
    updateBoardState: (Int, Int) -> Int,
    updateGameState: (Int)-> Unit
) {
    val matrix  = remember { gameMatrix }
    Surface(
        modifier = Modifier.Companion.size(300.dp)
    ) {

        Column(
            modifier = Modifier.Companion.fillMaxWidth(),
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
        ) {
            for ((i, rows) in matrix.withIndex()) {
                Row(
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = if (i == 2) 0.dp else 4.dp),

                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    for ((j, col) in rows.withIndex()) {
                        TextButton(
                            modifier = Modifier.Companion
                                .fillMaxHeight()
                                .background(color = BackGroundColor)
                                .weight(1f),
                            shape = RectangleShape,

                            onClick = {
                                updateGameState(updateBoardState(i, j))
                            },
                            enabled = matrix[i][j].isEmpty()
                        ) {
                            if (col == "X") {
                                Image(
                                    painter = painterResource(id = R.drawable.cross),
                                    contentDescription = "cross"
                                )
                            } else if (col == "O") {
                                Image(
                                    painter = painterResource(id = R.drawable.circle),
                                    contentDescription = "circle"
                                )
                            }
                        }

                        Spacer(modifier = Modifier.Companion.width(if (j == 2) 0.dp else 4.dp))
                    }


                }

            }
        }
    }
}