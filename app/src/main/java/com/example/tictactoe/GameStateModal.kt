package com.example.tictactoe

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class GameState(
    val playerScores: SnapshotStateList<Int> = mutableStateListOf(0 , 0),
    var winner: Int = 0,
)

class GameStateRepository {
    private var _gameState = GameState()

    fun getGameState() = _gameState

    fun initialState(){
        _gameState.playerScores[0] = 0
        _gameState.playerScores[1] = 0
        _gameState.winner = 0
    }

    fun updatePlayerScore(player: Int){
        _gameState.playerScores[player]++;
    }

    fun checkWinner(): Boolean {
        if (_gameState.playerScores[0] == 3) {
            _gameState.winner = 1
            return true
        } else if (_gameState.playerScores[1] == 3) {
            _gameState.winner = 2
            return true
        }
        return false
    }
}