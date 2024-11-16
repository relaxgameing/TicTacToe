package com.example.tictactoe.State

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModal(private  val repository : GameStateRepository) : ViewModel() {

    private val _gameState = mutableStateOf(repository.getGameState())
    val gameState: State<GameState> = _gameState


    fun updateGameState(roundWinner:Int){
        if (roundWinner <= 0 ){
            return
        }
        repository.updatePlayerScore(roundWinner - 1)
        repository.checkWinner()
        _gameState.value = repository.getGameState()
    }

    fun initialGameState(){
        repository.initialState()
    }

    fun checkWinner(): Boolean{
        return repository.checkWinner()
    }

}