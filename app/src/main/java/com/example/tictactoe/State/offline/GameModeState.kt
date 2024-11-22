package com.example.tictactoe.State.offline

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