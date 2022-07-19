package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerGameInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerPlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame extends CasinoAccountManager implements GamblerGameInterface {

    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private final IOConsole consoleASCII = new IOConsole(AnsiColor.BLUE);
    public List<GamblerPlayerInterface> gambler = new ArrayList<>();
    private Integer balance;




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

    @Override
    public void run() {
        try {
            welcome();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            to();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            slotsText();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Welcome to Slots! " + "\uD83C\uDFB0" + "\n" +
                "Your balance is " + this.balance);
        boolean running = true;
        while (running) {
            int bet = this.setBet(gambler.get(0));
            if (bet == 1) this.slots(gambler.get(0));
            else {
                String done = console.getStringInput("You can't play without inserting a dollar. Would you like to stay?");
                if (done.equals("no")) {
                    this.remove(gambler.get(0));
                }
            }
        }
    }

    public void slots(GamblerPlayerInterface player) {
        int[] lever = Lever.PullLever();

        String result = getResult(lever);
        if (result.equals("bingo")) {
            try {
                winner();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            win(player, 25);
        }
        if (result.equals("bango")) {
            try {
                winner();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            win(player, 10);
        }
        if (result.equals("bongo")) lose(player);

    }

    public String getResult(int[] pulled) {
        if (pulled[0] == pulled[1] && pulled[0] == pulled[2]) return "bingo";
        else if (pulled[0] == pulled[1] || pulled[1] == pulled[2]) return "bango";
        else return "bongo";
    }

    public int setBet(GamblerPlayerInterface player) {
        String input = console.getStringInput("Input $1 to play!");
        if (input.equals("1")) {
            int balance = player.getArcadeAccount().getBalance();
            if (balance <= 0) return 0;
            else {
//                player.getArcadeAccount().setBalance(balance - 1);
                gambler.get(0).getArcadeAccount().setBalance(balance - 1);
                return 1;
            }
        }
        else return 0;
    }

    public void win(GamblerPlayerInterface player, int winnings) {
        int balance = player.getArcadeAccount().getBalance();
        this.balance = balance + winnings;
        player.getArcadeAccount().setBalance(this.balance);
        System.out.println("Congrats! You won! \n" +
                "\uD83D\uDCB0" +"\uD83D\uDCB0" + "\uD83D\uDCB0 \n" +
                "Your new balance is " + player.getArcadeAccount().getBalance());
    }

    public void lose(GamblerPlayerInterface player) {
        System.out.println("Better luck next time. \n" +
                "Your new balance is " + player.getArcadeAccount().getBalance());
    }
    public void winner() throws InterruptedException {
        consoleASCII.print("                U  ___ u   U  ___ u   U  ___ u   U  ___ u \n" +
                " __        __    \\/\"_ \\/    \\/\"_ \\/    \\/\"_ \\/    \\/\"_ \\/ \n" +
                " \\\"\\      /\"/    | | | |    | | | |    | | | |    | | | | \n" +
                " /\\ \\ /\\ / /\\.-,_| |_| |.-,_| |_| |.-,_| |_| |.-,_| |_| | \n" +
                "U  \\ V  V /  U\\_)-\\___/  \\_)-\\___/  \\_)-\\___/  \\_)-\\___/  \n" +
                ".-,_\\ /\\ /_,-.     \\\\         \\\\         \\\\         \\\\    \n" +
                " \\_)-'  '-(_/     (__)       (__)       (__)       (__)   \n" +
                "  __   __   U  ___ u   _   _                              \n" +
                "  \\ \\ / /    \\/\"_ \\/U |\"|u| |                             \n" +
                "   \\ V /     | | | | \\| |\\| |                             \n" +
                "  U_|\"|_u.-,_| |_| |  | |_| |                             \n" +
                "    |_|   \\_)-\\___/  <<\\___/                              \n" +
                ".-,//|(_       \\\\   (__) )(                               \n" +
                " \\_) (__)     (__)      (__)                              \n" +
                "                U  ___ u  _   _                           \n" +
                " __        __    \\/\"_ \\/ | \\ |\"|                          \n" +
                " \\\"\\      /\"/    | | | |<|  \\| |>                         \n" +
                " /\\ \\ /\\ / /\\.-,_| |_| |U| |\\  |u                         \n" +
                "U  \\ V  V /  U\\_)-\\___/  |_| \\_|                          \n" +
                ".-,_\\ /\\ /_,-.     \\\\    ||   \\\\,-.                       \n" +
                " \\_)-'  '-(_/     (__)   (_\")  (_/                        " + "\n");
        Thread.sleep(1000);
    }
    public void welcome() throws InterruptedException {
        consoleASCII.print(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
                        "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                        "| | _____  _____ | || |  _________   | || |   _____      | || |     ______   | || |     ____     | || | ____    ____ | || |  _________   | |\n" +
                        "| ||_   _||_   _|| || | |_   ___  |  | || |  |_   _|     | || |   .' ___  |  | || |   .'    `.   | || ||_   \\  /   _|| || | |_   ___  |  | |\n" +
                        "| |  | | /\\ | |  | || |   | |_  \\_|  | || |    | |       | || |  / .'   \\_|  | || |  /  .--.  \\  | || |  |   \\/   |  | || |   | |_  \\_|  | |\n" +
                        "| |  | |/  \\| |  | || |   |  _|  _   | || |    | |   _   | || |  | |         | || |  | |    | |  | || |  | |\\  /| |  | || |   |  _|  _   | |\n" +
                        "| |  |   /\\   |  | || |  _| |___/ |  | || |   _| |__/ |  | || |  \\ `.___.'\\  | || |  \\  `--'  /  | || | _| |_\\/_| |_ | || |  _| |___/ |  | |\n" +
                        "| |  |__/  \\__|  | || | |_________|  | || |  |________|  | || |   `._____.'  | || |   `.____.'   | || ||_____||_____|| || | |_________|  | |\n" +
                        "| |              | || |              | || |              | || |              | || |              | || |              | || |              | |\n" +
                        "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                        " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' " + "\n");
        Thread.sleep(1000);
    }
    public void to() throws InterruptedException {
        consoleASCII.print(" .----------------.  .----------------. \n" +
                        "| .--------------. || .--------------. |\n" +
                        "| |  _________   | || |     ____     | |\n" +
                        "| | |  _   _  |  | || |   .'    `.   | |\n" +
                        "| | |_/ | | \\_|  | || |  /  .--.  \\  | |\n" +
                        "| |     | |      | || |  | |    | |  | |\n" +
                        "| |    _| |_     | || |  \\  `--'  /  | |\n" +
                        "| |   |_____|    | || |   `.____.'   | |\n" +
                        "| |              | || |              | |\n" +
                        "| '--------------' || '--------------' |\n" +
                        " '----------------'  '----------------' " + "\n");
                Thread.sleep(1000);
    }
    public void slotsText() throws InterruptedException {
        consoleASCII.print(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
                        "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                        "| |    _______   | || |   _____      | || |     ____     | || |  _________   | || |    _______   | |\n" +
                        "| |   /  ___  |  | || |  |_   _|     | || |   .'    `.   | || | |  _   _  |  | || |   /  ___  |  | |\n" +
                        "| |  |  (__ \\_|  | || |    | |       | || |  /  .--.  \\  | || | |_/ | | \\_|  | || |  |  (__ \\_|  | |\n" +
                        "| |   '.___`-.   | || |    | |   _   | || |  | |    | |  | || |     | |      | || |   '.___`-.   | |\n" +
                        "| |  |`\\____) |  | || |   _| |__/ |  | || |  \\  `--'  /  | || |    _| |_     | || |  |`\\____) |  | |\n" +
                        "| |  |_______.'  | || |  |________|  | || |   `.____.'   | || |   |_____|    | || |  |_______.'  | |\n" +
                        "| |              | || |              | || |              | || |              | || |              | |\n" +
                        "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                        " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' " + "\n \n");
                Thread.sleep(1000);
    }


}