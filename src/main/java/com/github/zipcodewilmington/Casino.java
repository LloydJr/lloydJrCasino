package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.CrapsGame;
import com.github.zipcodewilmington.casino.games.BasicCraps.CrapsPlayer;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerGameInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerPlayerInterface;
import com.github.zipcodewilmington.casino.games.blackjack.BlackjackGame;
import com.github.zipcodewilmington.casino.games.blackjack.BlackjackPlayer;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import com.github.zipcodewilmington.casino.games.roulette.RouletteGame;
import com.github.zipcodewilmington.casino.games.roulette.RoulettePlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.casino.games.war.WarGame;
import com.github.zipcodewilmington.casino.games.war.WarGamePlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leon on 7/21/2020.
 */
public class Casino extends CasinoAccountManager implements Runnable {


    private final IOConsole console = new IOConsole(AnsiColor.PURPLE);
    private final IOConsole consoleASCII = new IOConsole(AnsiColor.CYAN);
    CasinoAccountManager casinoAccountManager = new CasinoAccountManager();

    List<PlayerInterface> casinoList = new ArrayList<>();
    List<GamblerPlayerInterface> casinoListGam = new ArrayList<>();
    private boolean quit = true;
    CasinoAccount casinoAccount;


    public Casino(PlayerInterface player) {
        this.casinoList.add(player);
    }

    public Casino(GamblerPlayerInterface player){
        this.casinoListGam.add(player);
    }

    public Casino() {

    }




    @Override
    public void run() {
        printCasino();
        if(!this.casinoListGam.isEmpty()){
            reRunGame(this.casinoListGam.get(0).getArcadeAccount());
        }

        if(!this.casinoList.isEmpty()){
            reRunGame(this.casinoList.get(0).getArcadeAccount());
        }
        while(quit) {
            startGameReal();
        }
    }




    private String getArcadeDashboardInput() {
        return console.getStringInput("Welcome!" +
                "\nFrom here, you can select any of the following options:\n" +
                "Please create an account before selecting a game." +
                "\n\t 1: create-account , 2: select-game ");
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Please select a Game!")
                .append("\n\t 1: SLOTS, 2: NUMBERGUESS, 3: BLACKJACK, 4: ROULETTE, 5: WAR, 6: BACKROOM")
                .toString());
    }



    public void reRunGame(CasinoAccount casinoAccount) {
        if(!this.casinoListGam.isEmpty()) {
            this.casinoAccount = this.casinoListGam.get(0).getArcadeAccount();
        }
        if(!this.casinoList.isEmpty()) {
            this.casinoAccount = this.casinoList.get(0).getArcadeAccount();
        }
        String gameSelection = getGameSelectionInput();
        System.out.println("Press 0 to quit");
            if (gameSelection.equalsIgnoreCase("1")) {
                this.playGamble(new SlotsGame(), new SlotsPlayer(this.casinoAccount));
            } else if (gameSelection.equalsIgnoreCase("3")) {
                this.playGamble(new BlackjackGame(), new BlackjackPlayer(this.casinoAccount));
            } else if (gameSelection.equalsIgnoreCase("4")) {
                this.playGamble(new RouletteGame(), new RoulettePlayer(this.casinoAccount));
            } else if (gameSelection.equalsIgnoreCase("2")) {
                this.play(new NumberGuessGame(), new NumberGuessPlayer(this.casinoAccount));
            } else if (gameSelection.equalsIgnoreCase("5")) {
                this.play(new WarGame(), new WarGamePlayer(this.casinoAccount));
            } else if (gameSelection.equalsIgnoreCase("6")) {
                this.playGamble(new CrapsGame(), new CrapsPlayer(this.casinoAccount));

            } else {
                console.getStringInput("Please select a game to play");
            }
            try {
                gameSelection = getArcadeDashboardInput();
            } catch (InputMismatchException e) {
                System.out.println("That is not a correct number");
            }
            if (gameSelection.equalsIgnoreCase("0")) {
                System.out.println("Good bye");
                System.exit(0);
            }
        }




    public void startGameReal() {
        boolean noAccount = true;
        String gameInput = getArcadeDashboardInput();
        if(gameInput.equalsIgnoreCase("2") && casinoAccountManager.casAcc.isEmpty()) {
            while(noAccount) {
                System.out.println("No account found");
                gameInput = getArcadeDashboardInput();
                if(gameInput.equalsIgnoreCase("1")){
                    break;
                }
            }
    }

        if(gameInput.equalsIgnoreCase("1")){
                String accountName = console.getStringInput("Enter a name for your account:");
                String accountPassword = console.getStringInput("Enter a password for your account");
                Integer accountBalance = console.getIntegerInput("Enter how much you want to deposit into your account");

                this.casinoAccount = casinoAccountManager.createAccount(accountName, accountPassword, accountBalance);
            }

        if (gameInput.equalsIgnoreCase("2")) {
                printCasino();
                String gameSelection = getGameSelectionInput();
                if (gameSelection.equalsIgnoreCase("1")) {
                    this.playGamble(new SlotsGame(), new SlotsPlayer(this.casinoAccount));
                } else if (gameSelection.equalsIgnoreCase("3")) {
                            this.playGamble(new BlackjackGame(), new BlackjackPlayer(this.casinoAccount));
                } else if (gameSelection.equalsIgnoreCase("4")) {
                    this.playGamble(new RouletteGame(), new RoulettePlayer(this.casinoAccount));
                } else if (gameSelection.equalsIgnoreCase("2")) {
                            this.play(new NumberGuessGame(), new NumberGuessPlayer(this.casinoAccount));
                } else if (gameSelection.equalsIgnoreCase("5")) {
                    this.play(new WarGame(), new WarGamePlayer(this.casinoAccount));
                } else if (gameSelection.equalsIgnoreCase("6")) {
                    this.playGamble(new CrapsGame(), new CrapsPlayer(this.casinoAccount));
                } else {
                    console.getStringInput("Please select a game to play");
                }
            }
    }

    //  GameInterface needs to switch to GamblerGameInterface

    public void playGamble(Object gameObject, Object playerObject){
        GamblerGameInterface game = (GamblerGameInterface) gameObject;
        GamblerPlayerInterface player = (GamblerPlayerInterface) playerObject;
        game.add(player);
        game.run();
    }

    public void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        PlayerInterface player = (PlayerInterface)playerObject;
        game.add(player);
        game.run();
    }

    public void moveGame() {
        String move = console.getStringInput("Do you want to play another game? Enter 3\n" +
                "Or quit casino and save your funds? Enter 4");
        if(move.equals("3")) {
            getGameSelectionInput();
        } else if (move.equals("4")) {  //TODO Need to create the else statement for not qutting
            System.out.println("Good bye");
            System.exit(0);
        }
    }


    public void printCasino() {

        consoleASCII.print(" ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ \n" +
                "▐░▌          ▐░▌       ▐░▌▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          \n" +
                "▐░▌          ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ \n" +
                "▐░▌          ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                "▐░▌          ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀  ▀▀▀▀▀▀▀▀▀█░▌\n" +
                "▐░▌          ▐░▌       ▐░▌▐░▌                    ▐░▌▐░▌       ▐░▌▐░▌     ▐░▌            ▐░▌\n" +
                "▐░█▄▄▄▄▄▄▄▄▄ ▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░▌      ▐░▌  ▄▄▄▄▄▄▄▄▄█░▌\n" +
                "▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀ \n" +
                "                                                                                           \n");
    }






}

