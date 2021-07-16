package com.example.driver_nim.ui.main;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

public class NimGame extends ViewModel {
    int remainingSticks = 0;
    int totalSticks = 0;
    boolean twoPlayer = false;
    boolean playerOneTurn = true;
    int computerChoice = 0;
    boolean winner = false;
    enum Player{PlayerOne,PlayerTwo,Computer};
    Player winningPlayer;


    public NimGame(){
        totalSticks = 10 + (int) ( Math.random()  * ((20 - 10) + 1) );
        remainingSticks = totalSticks;
    }

    public String displaySticks(){
        String output = "";
        for(int i=0;i<remainingSticks;i++){
          output = (output + "| ");
        }
        return output;
    }


    void takeTurn(int sticks){
        remainingSticks -= sticks;
    }


    void takeAiTurn() throws InterruptedException {
        Thread.sleep(1000);
       int diffFromFour = remainingSticks % 4;

        if (diffFromFour == 3) {
            remainingSticks = remainingSticks - 2;
            computerChoice = 2;
        }
        else if (diffFromFour == 2) {
            remainingSticks = remainingSticks - 1;
            computerChoice = 1;

        }
        else if (diffFromFour == 1) {
            remainingSticks = remainingSticks - 1;
            computerChoice = 1;

        }
        else if (diffFromFour == 0) {
            remainingSticks = remainingSticks - 3;
            computerChoice = 3;

        }
        playerOneTurn = true;
    }

    public int getComputerChoice(){
        return computerChoice;
    }

    public boolean Winner(){
        return winner;
    }

    public String getWinningPlayer(){
        String winner = "";
        switch(winningPlayer){
            case PlayerOne: winner = "Player One";
                break;
            case PlayerTwo: winner = "Player Two";
                break;
            case Computer: winner = "The Computer";
                break;
        }
        return winner;
    }

    public void setTwoPlayer(boolean set){
        twoPlayer = set;
    }
    public boolean getPlayerOneTurn(){
        return playerOneTurn;
    }

    public boolean getTwoPlayer(){
        return twoPlayer;
    }

    public void playGame(int sticks) {
        if (remainingSticks != 0 && remainingSticks >= 0){
            takeTurn(sticks);
            playerOneTurn = !playerOneTurn ;
            if (!twoPlayer && remainingSticks !=0 && remainingSticks >= 0) {
                try {
                    takeAiTurn();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        if(remainingSticks <= 0){
            winner= true;
            if(playerOneTurn){
                winningPlayer = Player.PlayerOne;
            }
            if(!playerOneTurn && twoPlayer){
                winningPlayer = Player.PlayerTwo;
            }
            if(!playerOneTurn && !twoPlayer){
                winningPlayer = Player.Computer;
            }
        }
    }
}






