package com.github.zipcodewilmington.casino.games.card;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private int numOfCards;
    private final IOConsole console = new IOConsole(AnsiColor.AUTO);

    public Hand() {

    }

    public void emptyHand() {
        for (int i = 0; i < 10; i++) {
            this.hand.set(i, null);
        }
//            return (this.getHandSum() <= 21);
    }

    public void addCard (Card aCard){
        if (this.numOfCards < 10) {

            this.hand.add(aCard);
            numOfCards++;
        } else {
            System.out.println(this.printHandLimitMessage());
        }

//        this.numOfCards++;

    }

    public String printHandLimitMessage(){
        return "Too many cards";
    }


        public int getHandSum () {
            int handSum = 0;
            int cardNum;
            int numAces = 0;

            for (int i = 0; i < this.numOfCards; i++) {
                cardNum = this.hand.get(i).getValue();

                if (cardNum == 1) {
                    numAces++;

//                    int aceOrEleven = console.getIntegerInput("Enter 1 or 11 for your Ace value");
//                    if (aceOrEleven == 11){
//                        handSum += 11;
//
//                    } else if (aceOrEleven == 1){
//                        handSum += 1;
//                    }
//
//                } else if (cardNum > 10) {
//                    handSum += 10;
//                } else {
//                    handSum += cardNum;

                } else {
                    handSum += cardNum;
                }
            }
            return this.calculateAceValue(handSum, numAces);
        }

    private int calculateAceValue(int handSum, int numAces) {
        if(numAces > 1){
            if(handSum + 11 + (numAces - 1) <= 21){
                return handSum + 11 + (numAces - 1);
            } else {
                return handSum + numAces;
            }

        } else if(numAces == 1) {
            if(handSum + 11 <= 21){
                return handSum + 11;
            } else {
                return handSum + 1;
            }
        } else {
            return handSum;
        }
    }

    public void printHand( boolean showFirstCard){
//            System.out.println("%'s cards :\n", this.name);
        for (int i = 0; i < this.numOfCards; i++) {
            if (i == 0 && !showFirstCard) {
                System.out.println("  [hidden]  ");
            } else {
                System.out.printf("   %s\n", this.hand.get(i).toString());
            }
        }

    }


    public Integer handSize(){
        return this.hand.size();
    }

    public List<Card> getCards(){
        return this.hand;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < this.hand.size(); i++){
            sb.append(this.hand.get(i).toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}








