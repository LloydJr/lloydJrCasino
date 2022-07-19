package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerPlayerInterface;
import com.github.zipcodewilmington.casino.games.card.Card;
import com.github.zipcodewilmington.utils.IOConsole;

public class BlackjackPlayer implements GamblerPlayerInterface {
    CasinoAccount casinoAccount;
//    IOConsole console = new IOConsole();
//    Integer currentBet = 0;
    public BlackjackPlayer(CasinoAccount casinoAccount) {

        this.casinoAccount = casinoAccount;
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return this.casinoAccount;
    }



//    @Override
//    public void Garbage() {
//
//    }
//
//    @Override
//    public void CasinoAccount() {
//
//    }
//
//    @Override
//    public void setBet() {
//        boolean go = true;
//        Integer userInput = 0;
//        while (go) {
//            System.out.println("You have $" + casinoAccount.getBalance());
//            userInput = console.getIntegerInput("Minimum bet is $5, Max bet is $25, \n How much would you like to wager?");
//            if (userInput >= 5 && userInput <= 25){
//                Integer setBet = this.casinoAccount.getBalance() - userInput;
//                System.out.println("You wagered $" + userInput);
//                go = false;
//            } else if (userInput < 5 ) {
//                System.out.println("You must bet $5 or more");
//            } else if (userInput > 26) {
//                System.out.println("Hold up there Jeff Bezos....this is a $25 max table");
//            }
//        }
//
//        this.currentBet = userInput;
////        this.casinoAccount.setBalance(this.casinoAccount.getBalance() - userInput);
//    }
//
//    @Override
//    public void cashIn() {
//
//    }
//
//    @Override
//    public void cashOut() {
//
//    }

}

