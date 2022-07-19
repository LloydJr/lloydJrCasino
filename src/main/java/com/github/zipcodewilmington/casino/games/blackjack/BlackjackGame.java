package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerGameInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerPlayerInterface;
import com.github.zipcodewilmington.casino.games.card.Card;
import com.github.zipcodewilmington.casino.games.card.Hand;
import com.github.zipcodewilmington.casino.games.card.Rank;
import com.github.zipcodewilmington.casino.games.card.Shoe;
import com.github.zipcodewilmington.casino.games.card.deck.Deck;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static com.github.zipcodewilmington.casino.games.card.Suit.SPADE;

public class BlackjackGame extends Deck implements GamblerGameInterface {

    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_Blue = "\u001b[34m";
    private Hand dealersHand = new Hand();
    private Hand playerHand = new Hand();
    private Shoe shoe = new Shoe();
    private Boolean isRunning = false;
    private Boolean dealerDefaultWin = false;
    private Boolean dealersTurn = false;
    private Boolean stillPlaying = false;
    public Integer userInput;
    public Integer setBet = 0;
    public String userString;

    private final IOConsole console = new IOConsole(AnsiColor.AUTO);
    public BlackjackPlayer blackjackPlayer;
    public Integer balance;
    public List<GamblerPlayerInterface> gambler = new ArrayList<>();
    private boolean dealerWin;


    @Override
    public void add(GamblerPlayerInterface player) {
        player.getArcadeAccount();
        this.balance = player.getArcadeAccount().getBalance();
        gambler.add(player);
    }

    @Override
    public void remove(GamblerPlayerInterface player) {
        gambler.get(0).getArcadeAccount().setBalance(this.balance);
        Casino c = new Casino(player);
        c.run();
    }

    public void run() {
        printBlackJack();
        setBet();
        initializeNewGame();
        while (isRunning) {
            dealHands();
            checkDealerWinDefault();
            playerTurn(); //<--- this would basically become 'player turn'
            checkDealer(); //<---  essentially becomes 'dealer turn'
//            checkWinner();
            confirmNextHand();
        }
    }

//    public void  oldrun(){
//        this.initializeNewGame();
//        while (stillPlaying){
////            this.blackjackPlayer.setBet();
//            blackjackPlayer.setBet();
//            this.dealHands();
//            checkDealerWinDefault();
//            while(this.isRunning){
//                System.out.println(ANSI_YELLOW  + "Successfully started game");
////                blackjackPlayer.setBet();
//                this.advanceTurn();
////                checkDealerWinDefault();
//
//
////                this.advanceTurn();
//            }
//            this.checkWinner();
//            this.confirmNextHand();
//        }
//    }

    private void confirmNextHand() {
        Integer answer = this.console.getIntegerInput("1) Continue\n2) Leave");
        this.stillPlaying = answer == 1;
        this.isRunning = this.stillPlaying;
    }

    private void playerTurn() {
        Integer userInput = 0;
        while (userInput != 2 && this.isRunning) {
            console.println(ANSI_Blue + "Player's Turn. Current Hand:");
            System.out.println(ANSI_Blue + this.playerHand.toString());
            System.out.println(ANSI_Blue + "Hand value: " + playerHand.getHandSum() + "\n");
            userInput = this.console.getIntegerInput("Please enter your choice\n1: Hit\n2: Stand");
            if (userInput == 1) {
                this.playerHand.addCard(shoe.drawNext());
                System.out.println("Added card");
                this.busted();
            } else if (userInput == 2) {
                this.dealersTurn = true;
                console.println(ANSI_RED + "Dealers Turn. Current Hand:");
                System.out.println(ANSI_RED + this.dealersHand.toString());
                System.out.println(ANSI_RED + "Dealer has: " + this.dealersHand.getHandSum());
                this.checkDealer();
                System.out.println("-------End of round-----");

            }
        }
    }

////    private void advanceTurn() {
////
////        if(this.dealersTurn){
////
////            System.out.println(this.dealersHand.toString());
////            this.checkDealer();
////        } else {
////            //player's code
////            Integer userInput = 0;
////            while(userInput != 2 && this.isRunning){
////                console.println(ANSI_Blue + "Player's Turn. Current Hand:");
////                System.out.println(ANSI_Blue + this.playerHand.toString());
////                System.out.println(ANSI_Blue + "Hand value: " + playerHand.getHandSum() + "\n");
////                userInput = this.console.getIntegerInput("Please enter your choice\n1: Hit\n2: Stand");
////                if(userInput == 1){
////                    this.playerHand.addCard(shoe.drawNext());
////                    System.out.println("Added card");
////
////                    this.busted();
////                } else if (userInput == 2) {
//////                    System.out.println("_______Player = 2--------");
////                    this.dealersTurn = true;
////                    console.println(ANSI_RED + "Dealers Turn. Current Hand:");
////                    System.out.println(ANSI_RED + this.dealersHand.toString());
//////                    System.out.println("-----___display dealer hand__ just ran-----");
////                    System.out.println(ANSI_RED + "Dealer has: " + this.dealersHand.getHandSum());
////                    this.checkDealer();
////                    System.out.println("-------End of round-----");
////                    this.isRunning = false;
////                }
////            }
////        }
//
//        this.dealersTurn = !this.dealersTurn;
////        this.isRunning = !this.checkDealerWinDefault();
//    }


    public void initializeNewGame() {
        this.initializeHands();
        this.isRunning = true;
        this.stillPlaying = true;
//        this.isRunning = !this.checkDealerWinDefault();

    }

    private Boolean checkDealerWinDefault() {
        Boolean dealerDefaultWin = false;

        if (this.dealersHand.getCards().get(0).getRank().getRankName().equals("Ace")) {
            dealerDefaultWin = this.dealersHand.getCards().get(1).getRank().getRankValue() == 10;
        }
        this.dealerDefaultWin = dealerDefaultWin;
        return dealerDefaultWin;
    }

    private void dealHands() {
        System.out.println("Hitting dealInitial Hands");
        this.playerHand.emptyHand();
        this.dealersHand.emptyHand();

        this.playerHand.addCard(shoe.drawNext());
        this.dealersHand.addCard(shoe.drawNext());
        this.playerHand.addCard(shoe.drawNext());
        this.dealersHand.addCard(shoe.drawNext());
//        this.dealersHand.getCards().get(1).setFaceUp(true);
    }

    public void initializeHands() {
        this.dealersHand = new Hand();
        this.playerHand = new Hand();
    }

    public void busted() {
        if (this.playerHand.getHandSum() > 21) {
            System.out.println("You went over 21, and lost your bet of... " + this.userInput);
            System.out.println("You now have... " + this.balance);
            System.out.println(this.playerHand.toString());
            quit();
        }
    }


    public String printEndingMessage() {
        return "Game Over";
    }

    public void blackjack() {
    }

    public void checkWinner() {
        int winner;
        if (this.dealerDefaultWin) {
            System.out.println(ANSI_Blue + this.playerHand.toString());
            System.out.println(ANSI_RED + this.dealersHand.toString());
            //Pay the player and tell them
            System.out.println("Dealer won by 1 default");
            this.loseMoney(this.balance);
        } else if (this.playerHand.getHandSum() > this.dealersHand.getHandSum() && this.playerHand.getHandSum() <= 21) {
            System.out.println("Player1 wins");
            numberGameWinner();
            this.winMoney(this.balance);
        } else if (this.playerHand.getHandSum() == this.dealersHand.getHandSum()) {
            System.out.println("tie");
            // player keeps his current bet
            //no money is deducted from players account
        } else if (this.dealersHand.getHandSum() > this.playerHand.getHandSum() && this.dealersHand.getHandSum() <= 21) {
            System.out.println("Dealer wins");
            this.loseMoney(balance);
            run();
        }

    }

    public void checkDealer() {
        Boolean check = true;
        while (dealersHand.getHandSum() <= 17) {
            System.out.println(ANSI_RED + "------HAND IS LESS THAN 17-------\n");
            System.out.println(ANSI_RED + "dealer must hit");
            dealersHand.addCard(shoe.drawNext());
            System.out.println(dealersHand.toString());
            if (dealersHand.getHandSum() > 17 && playerHand.getHandSum() < dealersHand.getHandSum()) {
                checkWinner();
            } else {
                dealersHand.addCard(shoe.drawNext());
                System.out.println(dealersHand.toString());
                checkWinner();
            }
        }
        while (check) {
            boolean setFreat = true;
            if (dealersHand.getHandSum() <= 21 && dealersHand.getHandSum() >= 17 && dealersHand.getHandSum() > playerHand.getHandSum()) {
                this.loseMoney(this.setBet);
                System.out.println(ANSI_RED + "Dealer has " + dealersHand.getHandSum());
                checkWinner();
                break;
            } else if (dealersHand.getHandSum() == playerHand.getHandSum()) {
                System.out.println("Tie");
                checkWinner();
                quit();
//                check = false;
            } else if (dealersHand.getHandSum() > 21) {
                System.out.println(ANSI_RED + " Dealer busted!!");
                this.winMoney(this.setBet);
                checkWinner();
                quit();
            }
        }
    }

    public void quit() {
        boolean setFreat = true;
        userString = console.getStringInput("Do you want to quit?");
        if (userString.equalsIgnoreCase("yes")) {
            remove(gambler.get(0));
        } else if (userString.equalsIgnoreCase("no")) {
            run();
        } else {
            while (setFreat) {
                userString = console.getStringInput("That is not a number.\nDo you want to quit?");
                if (userString.equals("yes")) {
                    remove(gambler.get(0));
                    setFreat = false;
                } else if (userString.equals("no")) {
                    run();
                }
            }
        }
    }

    public void loseMoney(Integer bet) {
        this.gambler.get(0).getArcadeAccount().setBalance(this.balance - this.setBet);
    }

    public void winMoney(Integer winnings) {
        this.gambler.get(0).getArcadeAccount().setBalance(this.balance + (2*this.setBet));
    }

    public void setBet() {
        boolean go = true;
        while (go) {
            System.out.println("You have $" + this.balance);
            userInput = console.getIntegerInput("Minimum bet is $5, Max bet is $25, \n How much would you like to wager?");
            if (userInput >= 5 && userInput <= 25) {
                this.balance = this.balance - this.userInput;
                System.out.println("You wagered $" + this.userInput);
                go = false;
            } else if (userInput < 5) {
                System.out.println("You must bet $5 or more");
            } else if (userInput > 26) {
                System.out.println("Hold up there Jeff Bezos....this is a $25 max table");
            }
        }
    }

//        this.casinoAccount.setBalance(this.casinoAccount.getBalance() - userInput);


    public void win(PlayerInterface player, int winnings) {
//        if(playerHand.getHandSum() < dealersHand.getHandSum())

    }

    //       //collect player winnings
//        //add to account
//    }
    public void losings(PlayerInterface player, int losings) {

    }
    //collect losings
    //deduct from account


    public Shoe getShoe() {
        return null;

    }


    public void printBlackJack() {
        console.print("__/\\\\\\\\\\\\\\\\\\\\\\\\\\____/\\\\\\_________________/\\\\\\\\\\\\\\\\\\___________/\\\\\\\\\\\\\\\\\\__/\\\\\\________/\\\\\\______/\\\\\\\\\\\\\\\\\\\\\\_____/\\\\\\\\\\\\\\\\\\___________/\\\\\\\\\\\\\\\\\\__/\\\\\\________/\\\\\\_        \n" +
                " _\\/\\\\\\/////////\\\\\\_\\/\\\\\\_______________/\\\\\\\\\\\\\\\\\\\\\\\\\\______/\\\\\\////////__\\/\\\\\\_____/\\\\\\//______\\/////\\\\\\///____/\\\\\\\\\\\\\\\\\\\\\\\\\\______/\\\\\\////////__\\/\\\\\\_____/\\\\\\//__       \n" +
                "  _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\______________/\\\\\\/////////\\\\\\___/\\\\\\/___________\\/\\\\\\__/\\\\\\//_____________\\/\\\\\\______/\\\\\\/////////\\\\\\___/\\\\\\/___________\\/\\\\\\__/\\\\\\//_____      \n" +
                "   _\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\_____________\\/\\\\\\_______\\/\\\\\\__/\\\\\\_____________\\/\\\\\\\\\\\\//\\\\\\_____________\\/\\\\\\_____\\/\\\\\\_______\\/\\\\\\__/\\\\\\_____________\\/\\\\\\\\\\\\//\\\\\\_____     \n" +
                "    _\\/\\\\\\/////////\\\\\\_\\/\\\\\\_____________\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\_____________\\/\\\\\\//_\\//\\\\\\____________\\/\\\\\\_____\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\_____________\\/\\\\\\//_\\//\\\\\\____    \n" +
                "     _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_____________\\/\\\\\\/////////\\\\\\_\\//\\\\\\____________\\/\\\\\\____\\//\\\\\\___________\\/\\\\\\_____\\/\\\\\\/////////\\\\\\_\\//\\\\\\____________\\/\\\\\\____\\//\\\\\\___   \n" +
                "      _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_____________\\/\\\\\\_______\\/\\\\\\__\\///\\\\\\__________\\/\\\\\\_____\\//\\\\\\___/\\\\\\___\\/\\\\\\_____\\/\\\\\\_______\\/\\\\\\__\\///\\\\\\__________\\/\\\\\\_____\\//\\\\\\__  \n" +
                "       _\\/\\\\\\\\\\\\\\\\\\\\\\\\\\/__\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\_______\\/\\\\\\____\\////\\\\\\\\\\\\\\\\\\_\\/\\\\\\______\\//\\\\\\_\\//\\\\\\\\\\\\\\\\\\______\\/\\\\\\_______\\/\\\\\\____\\////\\\\\\\\\\\\\\\\\\_\\/\\\\\\______\\//\\\\\\_ \n" +
                "        _\\/////////////____\\///////////////__\\///________\\///________\\/////////__\\///________\\///___\\/////////_______\\///________\\///________\\/////////__\\///________\\///_\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void numberGameWinner() {
        console.print("\n" +
                "              .-=========-.\n" +
                "              \\'-=======-'/\n" +
                "              _|   .=.   |_\n" +
                "             ((|  {{1}}  |))\n" +
                "              \\|   /|\\   |/\n" +
                "               \\__ '`' __/\n" +
                "                 _`) (`_\n" +
                "               _/_______\\_\n" +
                "              /___________\\\n\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
//


        public String toString () {
            return null;
        }

}