package com.example.tic_tac_toe.logic

class Game(var score1:Int = 0, var score2:Int = 0) {

    private val arr = Array(3){Array(3){""} }
    private var player1Sign:String = "X"
    private var player2Sign:String = "O"
    private var winner:String = "Draw"
    private var player1Score=0;
    private var player2Score=0;
    private var currentPlayer: Boolean = true   //true means player 1 turn


    fun blockEmpty(i: Int, j: Int): Boolean {

        if(arr[i][j]=="")
            return true
        return false
    }

    fun getValue(i: Int, j: Int): String {

        //if block is empty, then perform

            arr[i][j] = getCurrentPlayerSign();
            currentPlayer=!currentPlayer        //change current player

            //checkWonOrFull()
            return arr[i][j]
    }

    fun getCurrentPlayerSign(): String {
        if(currentPlayer)
            return player1Sign
        else
            return player2Sign
    }

    fun getaWinner(): String {
        return winner
    }

    fun checkWonOrFull() : String{

        var won:Boolean=false;

        //rows
        if((arr[0][0] == "X" || arr[0][0] == "O") && arr[0][0] == arr[0][1] && arr[0][0] == arr[0][2])
            won=true;
        else if((arr[1][0] == "X" || arr[1][0] == "O") && arr[1][0] == arr[1][1] && arr[1][0] == arr[1][2])
            won=true;
        else if((arr[2][0] == "X" || arr[2][0] == "O") && arr[2][0] == arr[2][1] && arr[2][0] == arr[2][2])
            won=true;

        //columns
        else if((arr[0][0] == "X" || arr[0][0] == "O") && arr[0][0] == arr[1][0] && arr[0][0] == arr[2][0])
            won=true;
        else if((arr[0][1] == "X" || arr[0][1] == "O") && arr[0][1] == arr[1][1] && arr[0][1] == arr[2][1])
            won=true;
        else if((arr[0][2] == "X" || arr[0][2] == "O") && arr[0][2] == arr[1][2] && arr[0][2] == arr[2][2])
            won=true;

        //diagonals
        else if((arr[0][0] == "X" || arr[0][0] == "O") && arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2])
            won=true;
        else if((arr[0][2] == "X" || arr[0][2] == "O") && arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0])
            won=true;

        if (won)
        {
            currentPlayer=!currentPlayer
            if (getCurrentPlayerSign()==player1Sign)
            {
                winner="Player ${player1Sign} WON "
                player1Score++;
            }
            else if(getCurrentPlayerSign()==player2Sign)
            {
                winner="Player ${player2Sign} WON "
                player2Score++;
            }

            return "won"
        }
        else
        {
            for(i in 0..2)
            {
                for(j in 0..2)
                {
                    if(arr[i][j]=="")
                        return "none"
                }
            }

            return "full"
        }


    }

    fun resetGame()
    {
        for (i in 0..2)
        {
            for(j in 0..2)
            {
                arr[i][j]=""
            }
        }
    }

    fun getaPlayer1Score():Int{return player1Score}
    fun getaPlayer2Score():Int{return player2Score}
    fun getaPlayer1Sign():String{return player1Sign}
    fun getaPlayer2Sign():String{return player2Sign}
    fun setaPlayer1Score(){player1Score=0}
    fun setaPlayer2Score(){player2Score=0}



}