package com.example.tictactoe


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.tictactoe.Components.GameScreen
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameViewModel: GameViewModal = GameViewModal(GameStateRepository())
        val boardViewModal: BoardViewModal = BoardViewModal(BoardStateRepository())
        setContent {
            TicTacToeTheme {
                App(gameViewModel, boardViewModal)
            }
        }
    }
}

@Composable
fun App(
    gameViewModal: GameViewModal,
    boardViewModal: BoardViewModal,
    modifier: Modifier = Modifier
) {
    val newNavController = rememberNavController()

    NavHost(navController = newNavController, startDestination = "home") {
        composable("home") {
            MainMenu { newNavController.navigate("classic") }

        }

        composable("classic") {
            GameScreen(gameViewModal, boardViewModal) {
                newNavController.navigate("home")
            }
        }
    }
}