package com.example.tictactoe.supabase

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import com.example.tictactoe.R
import com.example.tictactoe.supabase.dataModel.Country
import com.example.tictactoe.supabase.dataModel.RoomModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.ktor.websocket.Frame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class Supabase( supabaseUrl: String ,  supabaseKey:String) {

    val supabaseClient  = createClient(supabaseUrl , supabaseKey)

    fun createClient(supabaseUrl: String, supabaseKey: String): SupabaseClient{
        val supabase = createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ) {
            install(Postgrest)
        }

        return supabase
    }

    @Composable
      fun CreateRoom(){
         val defaultRoom  = remember {  RoomModel(id = 0  , roomToken = "" , player1Connection = null , player2Connection = null)}
        var countries =  remember { mutableStateOf<List<Country>>(listOf()) }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
//                countries.value = supabaseClient.from("countries")
//                    .select().decodeList<Country>()
//                Log.i("supabase" , countries.value.toString())

                val result= supabaseClient.from("room").insert(defaultRoom).decodeList<RoomModel>()
                Log.d("supabase" , result.toString())
            }
        }

        Text("executed")
        LazyColumn() {
            items(
                countries.value,
                key = { country -> country.id },
            ) { country ->
                Text(
                    country.name,
                    modifier = Modifier.padding(8.dp),
                )
            }


        }
    }

    fun createAndSubscribeToChannel(channelName:String){
    }
}