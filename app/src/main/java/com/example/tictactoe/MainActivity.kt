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

        val classicGameState = GameModeState()
        val crazyGameState = GameModeState("crazy")
        setContent {
            TicTacToeTheme {
                App(classicGameState , crazyGameState)
            }
        }
    }
}

@Composable
fun App(
   classicGameState: GameModeState ,
   crazyGameState: GameModeState,
    modifier: Modifier = Modifier
) {
    val newNavController = rememberNavController()


    NavHost(navController = newNavController, startDestination = "home") {
        composable("home") {
            MainMenu { str -> newNavController.navigate(str) }
        }

        composable("classic") {
            GameScreen(classicGameState.getGameState(), classicGameState.getBoardState()) {
                newNavController.navigate("home")
            }
        }

        composable("crazy"){
            GameScreen(crazyGameState.getGameState() , crazyGameState.getBoardState()) {
                newNavController.navigate("home")
            }
        }
    }
}