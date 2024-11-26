package com.example.tictactoe.Screen

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.Components.AppLogo
import com.example.tictactoe.ui.theme.BackGroundColor
import com.example.tictactoe.ui.theme.BorderColor
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.ui.theme.kantiFontFamily

class HomeScreen(val  navigateTo: (String)-> Unit) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                MainMenu(navigateTo = navigateTo)
            }
        }
    }
}

@Composable
fun MainMenu(navigateTo: (String) -> Unit) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGroundColor),
            verticalArrangement = Arrangement.Center

        ) {
            AppLogo()
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center,

            ) {
                Menu(navigateTo)
            }
        }
    }
}

@Composable
fun Menu(navigateTo: (String) -> Unit) {
    MenuOption(navigateTo)
}

@Composable
fun MenuOption(navigateTo: (String) -> Unit, modifier: Modifier = Modifier) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
    ){


        H3("Select Mode")
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton (
            onClick = {navigateTo("classic")},
            colors = ButtonDefaults.outlinedButtonColors(containerColor = BorderColor, contentColor = Color.Black),
            border = null
        ) {
            H4("Classic")
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton (
            onClick = {navigateTo("crazy")},
            colors = ButtonDefaults.outlinedButtonColors(containerColor = BorderColor, contentColor = Color.Black),
            border = null
        ) {
            H4("Crazy")
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton (
            onClick = {navigateTo("online")},
            colors = ButtonDefaults.outlinedButtonColors(containerColor = BorderColor, contentColor = Color.Black),
            border = null
        ) {
            H4("online")
        }
    }
}

@Composable
fun H2(text:String , modifier: Modifier = Modifier) {
    Text(text , fontSize = 32.sp , fontFamily = kantiFontFamily)
}
@Composable
fun H3(text:String , modifier: Modifier = Modifier) {
    Text(text , fontSize = 24.sp , fontFamily = kantiFontFamily)
}

@Composable
fun H4(text:String , modifier: Modifier = Modifier) {
    Text(text , fontSize = 16.sp , fontFamily = kantiFontFamily)
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    TicTacToeTheme {
        MainMenu{}
    }
}