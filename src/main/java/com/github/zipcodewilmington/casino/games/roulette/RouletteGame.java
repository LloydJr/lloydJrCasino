package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerGameInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerPlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RouletteGame extends CasinoAccountManager implements GamblerGameInterface {

    int bet;
    int betOption;

    int selectNumber;

    int money = 1000;
    int roundCounter = 1;
    public List<GamblerPlayerInterface> gambler = new ArrayList<>();

    public Integer balance;
    BallNumberGenerator gen = new BallNumberGenerator();

    private final IOConsole console = new IOConsole(AnsiColor.RED);
    private final IOConsole consoleASCII = new IOConsole(AnsiColor.GREEN);





    @Override
    public void add(GamblerPlayerInterface player) {
        player.getArcadeAccount();
        this.balance = player.getArcadeAccount().getBalance();
        this.gambler.add(player);
    }

    @Override
    public void remove(GamblerPlayerInterface player) {
        this.gambler.get(0).getArcadeAccount().setBalance(this.balance);
        Casino c = new Casino(player);
        c.run();
    }

    @Override
    public void run() throws NumberFormatException {

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
            asciiRoulette();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        printSummary();
        console.print("Your bank account: " + this.balance + "\n");


        while (balance > 0) {
            bet = console.getIntegerInput("Please input the amount you desire to bet. (Enter 0 to quit game)");
            if (bet == 0) {
                this.gambler.get(0);
                remove(gambler.get(0));
                break;
            }
            betOption = console.getIntegerInput("Please select bet option. (Enter 0 for help)");

            if (betOption == 0) {
                printHelp();
//                bet = console.getIntegerInput("INVALID! Please input the amount you desire to bet. (Enter 0 to quit game)");
                betOption = console.getIntegerInput("Please select bet option. (Enter 0 for help)");
            }
            if (betOption > 10) {
//                bet = console.getIntegerInput("INVALID! Please input the amount you desire to bet. (Enter 0 to quit game)");
                betOption = console.getIntegerInput("Please select bet option. (Enter 0 for help)");
            } else if (betOption == 5) {
                selectNumber = console.getIntegerInput("Please select a number 0 - 37 [Type 37 is 00]");
            }

            RouletteBall ball;
            ball = gen.generator();
            winningMethod(bet, ball, selectNumber);

            console.print("Results: " + ball.getColor());

            console.print("Your bank account: " + this.balance + "\n");
            roundCounter++;

            console.print("Rounds: " + roundCounter + "\n");

            if (balance <= 0) {
                this.gambler.get(0);
                remove(gambler.get(0));
                break;
            }
        }

    }



    public void printSummary() {
        System.out.println("Below is the pay out of your desired option.");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| Bet              | Payout          | Probability   |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [1]Red           | 1 : 1           | ~47.6%        |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [2]Black         | 1 : 1           | ~47.3%        |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [3]Odd           | 1 : 1           | ~47.3%        |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [4]Even          | 1 : 1           | ~47.3%        |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [5]Single number | 35 : 1          | ~2.6%         |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [6]First Twelve  | 2 : 1           | ~31.5         |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [7]Second Twelve | 2 : 1           | ~31.5         |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [8]Third Twelve  | 2 : 1           | ~31.5         |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [9]  No. 1 - 18  | 1 : 1           | ~31.5         |");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| [10] No. 19 - 36 | 1 : 1           | ~31.5         |");
        System.out.println("+----------------------------------------------------+");
    }

    public void printHelp() {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| [1]Red | [2]Black | [3]Odd | [4]Even | [5]Single number |");
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| [6]First Twelve |  [7]Second Twelve  | [8]Third Twelve  |");
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|      [9]  No. 1 - 18    |      [10] No. 19 - 36         |");
            System.out.println("+---------------------------------------------------------+");
    }

    public void winningMethod(int bet, RouletteBall ball, int selectNumber) {
        //Bet is red and receive a 1 : 1 payout.
        if (betOption == 1 && ball.getNumber() % 2 != 0) this.balance = balance + bet;
            //Bet is black and receive a 1 : 1 payout.
        else if (betOption == 2 && ball.getNumber() % 2 == 0) this.balance = balance + bet;
            //Bet is odd and receive a 1 : 1 payout.
        else if (betOption == 3 && ball.getNumber() % 2 != 0) this.balance = balance + bet;
            //Bet is even and receive a 1 : 1 payout.
        else if (betOption == 4 && ball.getNumber() % 2 == 0) this.balance = balance + bet;
            //Bet is Single Number and receive a 35 : 1 payout.
        else if (betOption == 5 && ball.getNumber() == selectNumber) this.balance = balance + (bet * 35);
            //Bet is First Twelve and receive a 2 : 1 payout.
        else if (betOption == 6 && ball.getNumber() >= 1 && ball.getNumber() <= 12) this.balance = balance + (bet * 2);
            //Bet is Second Twelve and receive a 2 : 1 payout.
        else if (betOption == 7 && ball.getNumber() >= 13 && ball.getNumber() <= 24) this.balance = balance + (bet * 2);
            //Bet is Third Twelve and receive a 2 : 1 payout.
        else if (betOption == 8 && ball.getNumber() >= 25 && ball.getNumber() <= 36) this.balance = balance + (bet * 2);
            //Bet is No. 1 - 18 and receive a 1 : 1 payout.
        else if (betOption == 9 && ball.getNumber() >= 1 && ball.getNumber() <= 18) this.balance = balance + bet;
            //Bet is No. 19 - 36 and receive a 1 : 1 payout.
        else if (betOption == 10 && ball.getNumber() >= 19 && ball.getNumber() <= 36) this.balance = balance + bet;
            //Lose bet
        else this.balance = balance - bet;
    }

    public void welcome() throws InterruptedException {
        consoleASCII.print("                                                                                        \n" +
                "     ##### /    ##   ###            ###                                                 \n" +
                "  ######  /  #####    ###            ###                                                \n" +
                " /#   /  /     #####   ###            ##                                                \n" +
                "/    /  ##     # ##      ##           ##                                                \n" +
                "    /  ###     #         ##           ##                                                \n" +
                "   ##   ##     #         ##    /##    ##      /###      /###   ### /### /###     /##    \n" +
                "   ##   ##     #         ##   / ###   ##     / ###  /  / ###  / ##/ ###/ /##  / / ###   \n" +
                "   ##   ##     #         ##  /   ###  ##    /   ###/  /   ###/   ##  ###/ ###/ /   ###  \n" +
                "   ##   ##     #         ## ##    ### ##   ##        ##    ##    ##   ##   ## ##    ### \n" +
                "   ##   ##     #         ## ########  ##   ##        ##    ##    ##   ##   ## ########  \n" +
                "    ##  ##     #         ## #######   ##   ##        ##    ##    ##   ##   ## #######   \n" +
                "     ## #      #         /  ##        ##   ##        ##    ##    ##   ##   ## ##        \n" +
                "      ###      /##      /   ####    / ##   ###     / ##    ##    ##   ##   ## ####    / \n" +
                "       #######/ #######/     ######/  ### / ######/   ######     ###  ###  ### ######/  \n" +
                "         ####     ####        #####    ##/   #####     ####       ###  ###  ### #####   \n");
        Thread.sleep(1000);
    }

    public void to() throws InterruptedException {
        consoleASCII.print("                                     #                                                  \n" +
                "                                    ##                                                  \n" +
                "                                    ##                                                  \n" +
                "                                  ######## /###                                         \n" +
                "                                 ######## / ###  /                                      \n" +
                "                                    ##   /   ###/                                       \n" +
                "                                    ##  ##    ##                                        \n" +
                "                                    ##  ##    ##                                        \n" +
                "                                    ##  ##    ##                                        \n" +
                "                                    ##  ##    ##                                        \n" +
                "                                    ##  ##    ##                                        \n" +
                "                                    ##   ######                                         \n" +
                "                                     ##   ####                                          \n" +
                "                                                                                        \n");
        Thread.sleep(1000);
    }

    public void asciiRoulette() throws InterruptedException {
        consoleASCII.print("     ##### /##                        ###                                               \n" +
                "  ######  / ##                         ###                                              \n" +
                " /#   /  /  ##                          ##               #        #                     \n" +
                "/    /  /   ##                          ##              ##       ##                     \n" +
                "    /  /    /                           ##              ##       ##                     \n" +
                "   ## ##   /       /###   ##   ####     ##      /##   ######## ######## /##             \n" +
                "   ## ##  /       / ###  / ##    ###  / ##     / ### ######## ######## / ###            \n" +
                "   ## ###/       /   ###/  ##     ###/  ##    /   ###   ##       ##   /   ###           \n" +
                "   ## ##  ###   ##    ##   ##      ##   ##   ##    ###  ##       ##  ##    ###          \n" +
                "   ## ##    ##  ##    ##   ##      ##   ##   ########   ##       ##  ########           \n" +
                "   #  ##    ##  ##    ##   ##      ##   ##   #######    ##       ##  #######            \n" +
                "      /     ##  ##    ##   ##      ##   ##   ##         ##       ##  ##                 \n" +
                "  /##/      ### ##    ##   ##      /#   ##   ####    /  ##       ##  ####    /          \n" +
                " /  ####    ##   ######     ######/ ##  ### / ######/   ##       ##   ######/           \n" +
                "/    ##     #     ####       #####   ##  ##/   #####     ##       ##   #####            \n" +
                "#                                                                                       \n" +
                " ##                                                                                     \n");
        Thread.sleep(1000);
    }
}
