package com.example.tictactoe

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class GameState {
    var matrix =
        mutableStateListOf(
            mutableStateListOf("", "", ""),
            mutableStateListOf("", "", ""),
            mutableStateListOf("", "", "")
        )


    var player1Score = mutableIntStateOf(0)
    var player2Score = mutableIntStateOf(0)

    var winner = mutableStateOf("")
    var finalWinner = mutableIntStateOf(0)
    var isRoundOver = false

    var isPlayer1Turn = mutableStateOf(true)

    //index 0 is for X, index 1 is for O
    var colHash = arrayOf(
        arrayOf(0, 0),
        arrayOf(0, 0),
        arrayOf(0, 0)
    )

    //index 0 is for X, index 1 is for O
    var rowHash = arrayOf(
        arrayOf(0, 0),
        arrayOf(0, 0),
        arrayOf(0, 0)
    )

    fun backToInitialState() {
        resetRound()
        player1Score.intValue = 0
        player2Score.intValue = 0

        winner.value = ""
        finalWinner.intValue = 0
    }

    fun resetRound() {

        for (i in 0..2){
            for (j in 0..2)
                updateMatrix(i , j , "")
        }

        winner.value = ""
        isRoundOver = false
        isPlayer1Turn.value = true
        colHash = arrayOf(
            arrayOf(0, 0),
            arrayOf(0, 0),
            arrayOf(0, 0)
        )
        rowHash = arrayOf(
            arrayOf(0, 0),
            arrayOf(0, 0),
            arrayOf(0, 0)
        )
    }

    fun checkWinner(): Int {
        if (player1Score.intValue == 3) {
            winner.value = "player 1"
            return 1
        } else if (player2Score.intValue == 3) {
            winner.value = "player 2"
            return 2
        }
        return 0
    }

    fun updateGameState(row: Int, col: Int) {
        updateMatrix(row, col, if (isPlayer1Turn.value) "X" else "O")
        updateColHash(col, if (isPlayer1Turn.value) "X" else "O")
        updateRowHash(row, if (isPlayer1Turn.value) "X" else "O")
        isPlayer1Turn.value = !isPlayer1Turn.value

        val roundWinner = checkForThree()
        if (roundWinner != 0) {
            winner.value = "player $roundWinner"
        } else if (isBoardFull()) {
            resetRound()
        }
        val won = checkWinner()
        if (won != 0) {
            finalWinner.intValue = won
        }
    }

    fun checkForThree(): Int {

        for (col in colHash) {
            if (col[0] == 3) {
                player1Score.intValue++
                isRoundOver = true
                return 1
            } else if (col[1] == 3) {
                player2Score.intValue++
                isRoundOver = true
                return 2
            }
        }

        for (row in rowHash) {
            if (row[0] == 3) {
                player1Score.intValue++
                isRoundOver = true
                return 1
            } else if (row[1] == 3) {
                player2Score.intValue++
                isRoundOver = true
                return 2
            }
        }


        if (checkDiagonal("X")) {
            player1Score.intValue++
            isRoundOver = true
            return 1
        }

        if (checkDiagonal("O")) {
            player2Score.intValue++
            isRoundOver = true
            return 2
        }

        if (checkBackDiagonal("X")) {
            player1Score.intValue++
            isRoundOver = true
            return 1
        }

        if (checkBackDiagonal("O")) {
            player2Score.intValue++
            isRoundOver = true
            return 2
        }

        return 0

    }

    fun isBoardFull(): Boolean {
        for (col in colHash) {
            if (col[0] + col[1] != 3)
                return false
        }
        return true
    }

    fun checkBackDiagonal(value: String): Boolean {
        for (i in 0..2) {
            if (matrix[i][3 - i - 1] != value)
                return false
        }
        return true
    }

    fun checkDiagonal(value: String): Boolean {
        for (i in 0..2) {
            if (matrix[i][i] != value)
                return false
        }
        return true
    }

    fun updateMatrix(row: Int, col: Int, value: String) {
        matrix[row][col] = value
    }

    fun updateColHash(col: Int, value: String) {
        if (value == "X") {
            colHash[col][0]++
        } else
            colHash[col][1]++
    }

    fun updateRowHash(row: Int, value: String) {
        if (value == "X") {
            rowHash[row][0]++
        } else
            rowHash[row][1]++
    }


}