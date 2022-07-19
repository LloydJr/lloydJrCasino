package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.card.Card;
import com.github.zipcodewilmington.casino.games.card.Rank;
import com.github.zipcodewilmington.casino.games.card.deck.Deck;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarGame extends CasinoAccountManager implements GameInterface {

    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private Integer balance;
    public ArrayDeque<Card> dealerHand = new ArrayDeque<>();
    public ArrayDeque<Card> playerHand = new ArrayDeque<>();
    public ArrayDeque<Card> fieldDeck = new ArrayDeque<>();
    public List<PlayerInterface> player = new ArrayList<>();

    public ArrayList<Card> makeWarDeck () {
        Deck deck = new Deck();
        ArrayList<Card> warDeckUnShuffled = deck.getDeck();
        return warDeckUnShuffled;
    }
    public ArrayList<Card> makeWarDeckShuffled (ArrayList<Card> warDeckBeShuffled) {
        Collections.shuffle(warDeckBeShuffled);
        return warDeckBeShuffled;
    }
    public ArrayDeque<Card> makeDequeWarDeck (ArrayList<Card> warDeckBeShuffled) {
        ArrayDeque<Card> dequeDeck = new ArrayDeque<>(warDeckBeShuffled);
        return dequeDeck;
    }
    public ArrayDeque<Card> readyToUseWarDeck () {
        ArrayList<Card> unShuffledDeck = makeWarDeck();
        ArrayList<Card> shuffledDeck = makeWarDeckShuffled(unShuffledDeck);
        ArrayDeque<Card> dequeDeck = makeDequeWarDeck(shuffledDeck);
        return dequeDeck;
    }
    public void splitDeck (ArrayDeque<Card> dequeDeck) {
        for (int x= 0; x<52; x++) {
            if (x<26){
                dealerHand.addFirst(dequeDeck.removeFirst());
            } else {
                playerHand.addFirst(dequeDeck.removeFirst());
            }
        }
    }
    public void addThreeCardsToField (ArrayDeque<Card> bothPlayersDuringWar) {
        fieldDeck.addFirst(bothPlayersDuringWar.removeFirst());
        fieldDeck.addFirst(bothPlayersDuringWar.removeFirst());
        fieldDeck.addFirst(bothPlayersDuringWar.removeFirst());


    }
    public void collectCardsInField (ArrayDeque<Card> winnerOnly) {
        int x = 0;
        while (x==0) {
            if (fieldDeck.isEmpty()) {
                x=2;
            } else {
                winnerOnly.addLast(fieldDeck.removeFirst());
            }
        }
    }

    public void compareValueAndAdd (Card dealerCard, Card playerCard) {
        Rank rankDealer = dealerCard.getRank();
        int dealerC = rankDealer.getRankValue();
        Rank rankPlayer = playerCard.getRank();
        int playerC = rankPlayer.getRankValue();

        if (dealerC>playerC) {
            tankPrint();
            dealerHand.addLast(dealerCard);
            dealerHand.addLast(playerCard);
            collectCardsInField(dealerHand);
            System.out.println("Dealer has a " + dealerC +" .\n");
            System.out.println("Player has a " +playerC+" .\n");
            System.out.println("Dealer wins!");
            System.out.println("Dealer has " + dealerHand.size()+ " cards.\n");
            System.out.println("Player has " + playerHand.size()+ " cards.\n");

        } else if (dealerC<playerC) {
            tankPrint();
            playerHand.addLast(playerCard);
            playerHand.addLast(dealerCard);
            collectCardsInField(playerHand);
            System.out.println("Dealer has a " + dealerC +" .\n");
            System.out.println("Player has a " +playerC+" .\n");
            System.out.println("Player wins!");
            System.out.println("Dealer has " + dealerHand.size()+ " cards.\n");
            System.out.println("Player has " + playerHand.size()+ " cards.\n");

        } else if (dealerC == playerC) {
            System.out.println("Time for War!");
            fieldDeck.addFirst(playerCard);
            fieldDeck.addFirst(dealerCard);
            addThreeCardsToField(playerHand);
            addThreeCardsToField(dealerHand);
            System.out.println("Dealer has a " + dealerC +" .\n");
            System.out.println("Player has a " +playerC+" .\n");
            System.out.println("Dealer has " + dealerHand.size()+ " cards.\n");
            System.out.println("Player has " + playerHand.size()+ " cards.\n");
        } else {
            return;
        }

    }


    @Override
    public void run() {
        printTitle();
        System.out.println("Welcome to the game of War. Let's begin.\n");
        ArrayDeque<Card> deckRdy = readyToUseWarDeck();
        splitDeck(deckRdy);

        while (playerHand.size() > 0 && dealerHand.size() >0) {
            String flip = console.getStringInput("Enter f to flip a card or j to quit.");
            if (flip.equals("f")) {
                Card playerC = playerHand.removeFirst();
                Card dealerC = dealerHand.removeFirst();
                compareValueAndAdd(playerC,dealerC);
            } else if (flip.equals("j")) {
                remove(this.player.get(0));
            }

        }
        if (playerHand.size()==0) {
            System.out.println("You lose!");

        } else if (dealerHand.size()==0) {
            System.out.println("Congrats on winning!");

        }
        return;

    }

    @Override
    public void add(PlayerInterface player) {
        player.getArcadeAccount();
        this.balance = player.getArcadeAccount().getBalance();
        this.player.add(player);

    }

    @Override
    public void remove(PlayerInterface player) {
        this.player.get(0).getArcadeAccount().setBalance(this.balance);
        Casino c = new Casino(player);
        c.run();
    }

    public void tankPrint() {
        console.print("\n" +
                "\n" +
                "░░░░░░░░( •̪●)░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░███████ ]▄▄▄▄▄▄▄▄▃░░░▃░░░░ ▃░░\n" +
                "▂▄▅█████████▅▄▃▂░░░░░░░░░░░░░░░░░\n" +
                "I███████████████████].░░░░░░░░░░░░░░\n" +
                "◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲⊙◤...░░░░░░░░░░░░░░\n");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printTitle(){
        console.print(" _    _  ___  ______ \n" +
                "| |  | |/ _ \\ | ___ \\\n" +
                "| |  | / /_\\ \\| |_/ /\n" +
                "| |/\\| |  _  ||    / \n" +
                "\\  /\\  / | | || |\\ \\ \n" +
                " \\/  \\/\\_| |_/\\_| \\_|\n" +
                "                     \n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
