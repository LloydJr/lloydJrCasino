package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame extends CasinoAccountManager implements GameInterface {
    public static int playerGuess;
    public static int randomNumber;
    Scanner playerObject = new Scanner(System.in);
    String playerName;
    private final IOConsole console = new IOConsole();


    Integer balance;
    List<PlayerInterface> player = new ArrayList<>();



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

    @Override
    public void run() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(11) + 2;
        System.out.println("Random number is a secret");

        int counter = 0;
        numberGameTitle();
        while (true) {
            int playerGuess = console.getIntegerInput("Pick a number between 2 and 12: \n" +
                    "press 0 to leave");
            if (playerGuess == randomNumber) {
                numberGameWinner();
                System.out.println("Congratulations! You win!");
                System.out.println("It took you " + counter + " tries");

            } else if (playerGuess ==0){
                remove(this.player.get(0));

            } else {
                    System.out.println("Nope, guess again!");
                counter++;
                }
        }
    }

    public void numberGameTitle() {
        console.print("_____     __  __     ______     ______     ______     ______   __  __     ______     __   __     __  __     __    __     ______     ______     ______    \n" +
                "/\\  ___\\   /\\ \\/\\ \\   /\\  ___\\   /\\  ___\\   /\\  ___\\   /\\__  _\\ /\\ \\_\\ \\   /\\  ___\\   /\\ \"-.\\ \\   /\\ \\/\\ \\   /\\ \"-./  \\   /\\  == \\   /\\  ___\\   /\\  == \\   \n" +
                "\\ \\ \\__ \\  \\ \\ \\_\\ \\  \\ \\  __\\   \\ \\___  \\  \\ \\___  \\  \\/_/\\ \\/ \\ \\  __ \\  \\ \\  __\\   \\ \\ \\-.  \\  \\ \\ \\_\\ \\  \\ \\ \\-./\\ \\  \\ \\  __<   \\ \\  __\\   \\ \\  __<   \n" +
                " \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\/\\_____\\  \\/\\_____\\    \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\  \\ \\_____\\  \\ \\_\\ \\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\ \n" +
                "  \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_____/     \\/_/   \\/_/\\/_/   \\/_____/   \\/_/ \\/_/   \\/_____/   \\/_/  \\/_/   \\/_____/   \\/_____/   \\/_/ /_/ \n" +
                "                                                                                                                                                          \n" );
        try {
            Thread.sleep(500);
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
                "              /___________\\\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}

