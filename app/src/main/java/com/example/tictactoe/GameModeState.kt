package com.example.tictactoe

import com.example.tictactoe.State.BoardState
import com.example.tictactoe.State.BoardStateRepository
import com.example.tictactoe.State.BoardViewModal
import com.example.tictactoe.State.GameState
import com.example.tictactoe.State.GameStateRepository
import com.example.tictactoe.State.GameViewModal

class GameModeState(boardMode: String = "classic") {
    private val gameState: GameViewModal = GameViewModal(GameStateRepository())
    private val  boardState: BoardViewModal = BoardViewModal(BoardStateRepository(boardMode))
    fun getGameState(): GameViewModal{
        return gameState
    }

    fun getBoardState(): BoardViewModal{
        return boardState
    }
}