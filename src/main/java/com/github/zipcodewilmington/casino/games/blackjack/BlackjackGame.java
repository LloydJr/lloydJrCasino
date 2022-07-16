package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.card.Hand;
import com.github.zipcodewilmington.casino.games.card.Shoe;
import com.github.zipcodewilmington.casino.games.card.deck.Deck;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.LinkedList;
import java.util.Scanner;

public class BlackjackGame extends Deck implements GameInterface {

    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    private Hand dealersHand = new Hand();
    private Hand playerHand = new Hand();
    private Shoe shoe = new Shoe();
    private Boolean isRunning = false;
    private Boolean dealerDefaultWin = false;
    private Boolean dealersTurn = false;

    private final IOConsole console = new IOConsole(AnsiColor.AUTO);
    public LinkedList<BlackjackPlayer> blackjackPlayer = new LinkedList<>();
    private boolean dealerWin;




    public void add(PlayerInterface player) {
        blackjackPlayer.add((BlackjackPlayer) player);
        player.getArcadeAccount();
        this.balance = player.getArcadeAccount().getBalance();


    }

    public void  run(){
        this.initializeNewGame();
        checkDealerWinDefault();
        while(this.isRunning == true){
            System.out.println(ANSI_YELLOW  + "Successfully started game");


//            this.setBet();
            this.advanceTurn();
        }

        this.checkwinner();


//        this.determineWinner();
//        System.out.println((this.printEndingMessage()));

    }

    private void advanceTurn() {

        if(this.dealersTurn){

            System.out.println(this.dealersHand.toString());
            this.checkDealer();
        } else {
            //player's code
            Integer userInput = 0;
            while(userInput != 2 && this.isRunning){


                userInput = this.console.getIntegerInput("Please enter your choice\n1: Hit\n2: Stand");
                if(userInput == 1){
                    this.playerHand.addCard(shoe.drawNext());
                    System.out.println("Added card");

                    this.busted();
                } else if (userInput == 2) {

                    System.out.println("_______Player = 2--------");
                    this.dealersTurn = true;
                    console.println("Dealers Turn. Current Hand:");
                    System.out.println(this.dealersHand.toString());
                    System.out.println("-----___display dealer hand__ just ran-----");
                    System.out.println("Dealer has: " + this.dealersHand.getHandSum());
                    this.checkDealer();
                    System.out.println("---");
                    System.out.println("-------End of round-----");
                    this.isRunning = false;
                }
                //if something stand related needs to happen, set up here?
//                System.out.println("chose to stand");

            }
        }

        this.dealersTurn = !this.dealersTurn;
    }



    public void initializeNewGame() {
        this.initializeHands();
        this.dealInitialHands();
        this.isRunning = !this.checkDealerWinDefault();

    }

    private Boolean checkDealerWinDefault() {
        Boolean dealerDefaultWin = false;

        if(this.dealersHand.getCards().get(0).getRank().getRankName().equals("Ace")){
            dealerDefaultWin = this.dealersHand.getCards().get(1).getRank().getRankValue() == 10;
        }
        this.dealerDefaultWin = dealerDefaultWin;
        return dealerDefaultWin;
    }

    private void dealInitialHands() {
//        System.out.println("Hitting dealInitial Hands");
        this.playerHand.addCard(shoe.drawNext());
        this.dealersHand.addCard(shoe.drawNext());
        this.playerHand.addCard(shoe.drawNext());
        this.dealersHand.addCard(shoe.drawNext());
        this.dealersHand.getCards().get(1).setFaceUp(true);
    }

    public void initializeHands() {
//        System.out.println("Hitting initialize hands");
        this.dealersHand = new Hand();
        this.playerHand = new Hand();
    }

    public void busted() {
        if (this.playerHand.getHandSum() > 21) {

            System.out.println("you went over 21, and lost your bet ");
            System.out.println(this.playerHand.toString());
            this.dealerWin = true;
            this.isRunning = false;
        }
    }


    public String printEndingMessage() {
        return "Game Over";
    }

    public void blackjack(){
    }


//    public Scanner setBet(){
//
//        int bet;
//        String input = console.getStringInput("Minimum bet is $5!");
//        Scanner userInput = new Scanner(System.in);
//        while (userInput.nextDouble() > 5) {
//            System.out.println("You must bet $5 or more");
//        } if {(userInput.nextDouble() > 5) {
//            System.out.println("You bet " + userInput);
//        }
//
//        }
//        return userInput;
//
//
//    }
    public void checkwinner(){

        int winner;
        if(this.dealerDefaultWin) {
            //Pay the player and tell them
            System.out.println("Dealer won by default");
        } else if(playerHand.getHandSum() > dealersHand.getHandSum()){
            System.out.println( "Player1 wins");
        } else if (playerHand.getHandSum() == dealersHand.getHandSum()){
            System.out.println("tie");

        } else if (dealersHand.getHandSum() > playerHand.getHandSum()) {
            System.out.println("Dealer wins");

        }
    }

    public void checkDealer() {
//        console.println("Dealers Turn. Current Hand:");

        while (dealersHand.getHandSum() < 17) {
            System.out.println("------HAND IS LESS THAN 17-------");
            if (dealersHand.getHandSum() < 17) {
                System.out.println("dealer must hit");
                dealersHand.addCard(shoe.drawNext());
                System.out.println(dealersHand.toString());
                System.out.println("_____dealers hand ran_____\n next is 18-20");
            } else if (dealersHand.getHandSum() < 21 && dealersHand.getHandSum() > 17) {
                System.out.println("Dealer has " + dealersHand.getHandSum());
//                break;
//            give player choice to pay again && pay the player
            } else if (dealersHand.getHandSum() == playerHand.getHandSum()) {
                System.out.println("Tie");
//            replay game method here
            } else if (dealersHand.getHandSum() > playerHand.getHandSum()) {
                console.println("Dealers Turn. Current Hand:");
                System.out.println(this.dealersHand.toString());

            }
        }
    }


        public void win(PlayerInterface player, int winnings){

//       //collect player winnings
//        //add to account
//    }
    public void losings(PlayerInterface player, int losings){

    }
        //collect losings
        //deduct from account


    public Shoe getShoe() {
        return null;

    }




    @Override
    public void remove(PlayerInterface player) {
        blackjackPlayer.add((BlackjackPlayer) player);
    }



    public String toString() {
        return null;
    }

}




