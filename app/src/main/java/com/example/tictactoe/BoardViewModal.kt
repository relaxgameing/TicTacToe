package com.example.tictactoe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BoardViewModal(private val repository: BoardStateRepository): ViewModel() {

    private var _boardState = mutableStateOf(repository.getBoardState())
    val boardState: State<BoardState> = _boardState

    fun updateBoardState(row: Int , col:Int): Int{
        val checkThree = repository.updateBoardState(row , col)
        _boardState.value = repository.getBoardState()
        return checkThree
    }

    fun resetRound(){
        repository.resetRound()
        _boardState.value = repository.getBoardState()
        Log.d("debug" , "inside resetRound ${_boardState.value.isRoundOver}")
    }
}