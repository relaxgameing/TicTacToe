package com.example.tictactoe

import android.util.Log
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class BoardState(
    var board: SnapshotStateList<SnapshotStateList<String>> = mutableStateListOf(
        mutableStateListOf("", "", ""),
        mutableStateListOf("", "", ""),
        mutableStateListOf("", "", "")
    ),
    var isPlayer1Turn: MutableState<Boolean> =mutableStateOf(true),
    val rowHash: Array<Array<Int>> = arrayOf(
        arrayOf(0, 0),
        arrayOf(0, 0),
        arrayOf(0, 0)
    ),
    val colHash: Array<Array<Int>> = arrayOf(
        arrayOf(0, 0),
        arrayOf(0, 0),
        arrayOf(0, 0)
    ),
    val isBoardFull: MutableState<Boolean> = mutableStateOf(false),
    var isRoundOver: MutableState<Boolean> = mutableStateOf(false),
    var roundWinner: MutableIntState = mutableIntStateOf(0),
)

class BoardStateRepository{

    private  var _boardState  = BoardState()

    fun getBoardState() = _boardState

    fun resetRound() {
        _boardState.board.forEachIndexed { i, row ->
            row.forEachIndexed { j, _ ->
                _boardState.board[i][j] = ""
            }
        }

        // Reset row and column hashes
        for (i in 0..2) {
            _boardState.rowHash[i][0] = 0
            _boardState.rowHash[i][1] = 0
            _boardState.colHash[i][0] = 0
            _boardState.colHash[i][1] = 0
        }

        // Reset state values
        _boardState.isPlayer1Turn.value = true
        _boardState.isRoundOver.value = false
        _boardState.roundWinner.intValue = 0
        _boardState.isBoardFull.value = false
        Log.d("debug" , "inside resetRound in repository $_boardState")
    }

    fun updateRowHash(row:Int){
        if (_boardState.isPlayer1Turn.value)
            _boardState.rowHash[row][0]++
        else
            _boardState.rowHash[row][1]++
    }

    fun updateMatrix(row: Int, col: Int) {
        if (_boardState.isPlayer1Turn.value){
            _boardState.board[row][col] = "X"
        }
        else
            _boardState.board[row][col] = "O"
    }

    fun updateColHash(col:Int){
        if (_boardState.isPlayer1Turn.value)
            _boardState.colHash[col][0]++
        else
            _boardState.colHash[col][1]++
    }

    fun updateBoardState(row: Int, col: Int): Int {
        Log.d("debug" , "inside updateBoardState")
        updateMatrix(row, col)
        updateColHash(col)
        updateRowHash(row)
        _boardState.isPlayer1Turn.value = !_boardState.isPlayer1Turn.value
        val three = checkForThree()
        if (three != 0 ){
            _boardState.isRoundOver.value = true;
            _boardState.roundWinner.intValue  = three
        }

        return three
    }

    fun checkForThree(): Int {
        for (col in _boardState.colHash) {
            if (col[0] == 3) {
                _boardState.isRoundOver.value = true
                return 1
            } else if (col[1] == 3) {
                _boardState.isRoundOver.value = true
                return 2
            }
        }

        for (row in _boardState.rowHash) {
            if (row[0] == 3) {
                _boardState.isRoundOver.value = true
                return 1
            } else if (row[1] == 3) {
                _boardState.isRoundOver.value = true
                return 2
            }
        }


        if (checkDiagonal("X")) {
            _boardState.isRoundOver.value = true
            return 1
        }

        if (checkDiagonal("O")) {
            _boardState.isRoundOver.value = true
            return 2
        }

        if (checkBackDiagonal("X")) {
            _boardState.isRoundOver.value = true
            return 1
        }

        if (checkBackDiagonal("O")) {
            _boardState.isRoundOver.value = true
            return 2
        }

        if (checkIsBoardFull())
            return -1

        return 0

    }

    fun checkIsBoardFull(): Boolean{
        for (i in 0..2){
            if (_boardState.colHash[i][0] + _boardState.colHash[i][1] != 3)
                return false
        }
        return true
    }

    fun checkBackDiagonal(value: String): Boolean {
        for (i in 0..2) {
            if (_boardState.board[i][3 - i - 1] != value)
                return false
        }
        return true
    }

    fun checkDiagonal(value: String): Boolean {
        for (i in 0..2) {
            if (_boardState.board[i][i] != value)
                return false
        }
        return true
    }

}