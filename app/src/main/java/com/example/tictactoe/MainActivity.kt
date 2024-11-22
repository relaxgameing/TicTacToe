package com.example.tictactoe


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< Updated upstream
import com.example.tictactoe.Components.GameScreen
import com.example.tictactoe.supabase.Supabase
=======
import com.example.tictactoe.Screen.GameScreen
import com.example.tictactoe.Screen.MainMenu
import com.example.tictactoe.Screen.OnlineScreen
import com.example.tictactoe.State.offline.GameModeState
import com.example.tictactoe.State.online.OnlineModeState
>>>>>>> Stashed changes
import com.example.tictactoe.ui.theme.TicTacToeTheme
import io.github.jan.supabase.SupabaseClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val supabase = Supabase(getString(R.string.supabase_url) , getString(R.string.supabase_key))


        val classicGameState = GameModeState()
        val crazyGameState = GameModeState("crazy")
        val userStateViewModal = OnlineModeState()
       
        setContent {
            TicTacToeTheme {
<<<<<<< Updated upstream
//                App(classicGameState , crazyGameState)
                supabase.CreateRoom()
=======
                App(classicGameState , crazyGameState , userStateViewModal)
>>>>>>> Stashed changes
            }
        }
    }
}

@Composable
fun App(
    classicGameState: GameModeState,
    crazyGameState: GameModeState,
    onlineState: OnlineModeState,
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

        composable("online"){
            OnlineScreen(onlineState.userState , onlineState.roomState ){
                newNavController.navigate("home")
            }
        }
    }
}