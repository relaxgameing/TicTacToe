package com.example.tictactoe.State.offline

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.Server.TestResponse
import com.example.tictactoe.Server.retroService
import kotlinx.coroutines.async

class BoardViewModal(private val repository: BoardStateRepository) : ViewModel() {

    private var _boardState = mutableStateOf(repository.getBoardState())
    val boardState: State<BoardState> = _boardState
    var res: TestResponse = TestResponse()
    fun updateBoardState(row: Int, col: Int): Int {
        val checkThree = repository.updateBoardState(row, col)
        _boardState.value = repository.getBoardState()

        return checkThree
    }

     fun getData(){
        viewModelScope.async {
            Log.d("hello" , "running")
            val res = async{
                retroService.gameTest()
            }
            Log.d("hello", res.await().rooms.toString())
            res.join()
        }
    }

    fun resetRound() {
        repository.resetRound()
        _boardState.value = repository.getBoardState()
        Log.d("debug", "inside resetRound ${_boardState.value.isRoundOver}")
    }
}