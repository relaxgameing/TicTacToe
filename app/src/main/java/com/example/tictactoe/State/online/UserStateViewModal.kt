package com.example.tictactoe.State.online

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.Server.retroService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserStateViewModal(private  val userStateRepository: UserStateRepository): ViewModel() {
    private  var _user = mutableStateOf(UserStateModal())
    val user: State<UserStateModal> = _user

    fun getUsername(): String?{
        return _user.value.username
    }

    fun test(){
        viewModelScope.launch {
            try {
                Log.d("retro" , "running")
                val res = async{
                    retroService.gameTest()
                }
                Log.d("retro", res.await().rooms.toString())
            }catch (e : Exception){
                Log.e("retro" , e.message.toString())
            }

        }
    }

    fun checkUsername(username: String){
        viewModelScope.launch{
            Log.d("retro" , "checking")
            try {
            val res = async{ retroService.checkUsername(username) }
            if (res.await().isValidUsername){
                Log.d("retro" , "changing user name")
                _user.value = userStateRepository.updateUsername(username).copy()
            }

            Log.d("retro" , res.await().isValidUsername.toString())

            res.join()
            }catch (e: Exception){
                Log.i("retro" , e.message.toString())
            }
        }
    }

    fun createNewRoom(username: String){
        viewModelScope.launch{
            try {
                val res = async{retroService.createRoom(username)}
                val roomId = res.await().roomId
                Log.d("retro" , roomId.toString())
                if(roomId!= null){

                    _user.value = userStateRepository.updateRoomToken(roomId).copy()
                }

                res.join()
            }catch (e: Exception){
                Log.d("retro" , "error occure while making new room ${e.message}")
            }
        }
    }
}