package com.example.tictactoe.State.online

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.Server.retroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserStateViewModal(private val userStateRepository: UserStateRepository) : ViewModel() {
    private var _user = MutableStateFlow<UserStateModal>(UserStateModal())
    val user: StateFlow<UserStateModal> = _user.asStateFlow()

    fun getUsername(): String? {
        return _user.value.username
    }

    fun test() {
        viewModelScope.launch {
            try {
                Log.d("retro", "running")
                val res = async {
                    retroService.gameTest()
                }
                Log.d("retro", res.await().rooms.toString())
            } catch (e: Exception) {
                Log.e("retro", e.message.toString())
            }

        }
    }

    fun updateRoomMode(mode: String) {
        viewModelScope.launch {
            _user.emit(userStateRepository.updateRoomMode(mode).copy())
        }
    }

    fun checkUsername(username: String) {
        viewModelScope.launch {
            Log.d("retro", "checking")
            try {
                val res = async { retroService.checkUsername(username) }

                if (res.await().isValidUsername) {
                    Log.d("retro", "changing user name")
                    _user.emit(userStateRepository.updateUsername(username).copy())
                }
                res.join()
            } catch (e: Exception) {
                Log.i("retro", e.message.toString())
            }
        }
    }

    fun createNewRoom(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("retro", "creating room $username")
                val res = async {
                    retroService.createRoom(username)
                }


                val data = res.await()
                if (data.isSuccessful){
                    if (data.body() != null){
                        _user.emit( userStateRepository.updateRoomToken(data.body()!!.roomId).copy())
                    }
                }else{
                    Log.d("retro" , data.body().toString())
                }
             res.join()
            } catch (e: Exception) {
                Log.d("retro", "error occured while making new room ${e.message}")
            }
        }
    }

    fun joinRoom(roomToken: String? , username: String? , navigateToRoom:()-> Unit){
        if (roomToken.isNullOrBlank() || username.isNullOrBlank()) return
        Log.d("retro" , "joining")
        viewModelScope.launch{

        }
    }
}