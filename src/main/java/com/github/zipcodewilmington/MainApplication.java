package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.blackjack.BlackjackGame;

public class MainApplication {
    public static void main(String[] args) {
        BlackjackGame bjGame = new BlackjackGame();
//        BlackjackPlayer player = new BlackjackPlayer();


        bjGame.run();

    }
}
