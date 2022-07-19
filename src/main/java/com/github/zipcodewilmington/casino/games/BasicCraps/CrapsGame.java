package com.github.zipcodewilmington.casino.games.BasicCraps;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;

public class CrapsGame extends RandomPlayerBots implements GamblerGameInterface{



    private final IOConsole console = new IOConsole(AnsiColor.GREEN);

    boolean robMechanicOn = true;
    private Integer holdBets;

    private Integer balance;
    private Integer setBet;
    private int randomDiceRollRobbingProb;
    private Integer balance1 = 1000;


    private List<GamblerPlayerInterface> gambler = new ArrayList<>();

    DiceGenerator diceGenerator = new DiceGenerator();


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

    public Integer setBet(Integer bet){
        this.setBet = bet;
            while(bet<500) {
                    System.out.println("Not enough G, gotta go higher than that");
                    bet = console.getIntegerInput("Enter your bet, minimum bet $500");
            }
            this.balance = this.balance - this.setBet;
            return this.holdBets;
    }



    public Integer win(){
        System.out.print("Got the dub! ");
        printDice();
        gettingRobbedProbablity();
        return this.balance += (this.setBet * 2);
    }

    public void noMoney() {
        if(this.balance ==0 || this.balance<500){
            System.out.println("Better get some more bread, b/c your done here");
            remove(gambler.get(0));
        }
    }

    public void lose() {
        System.out.println("Do better next time");


    }

    public void quit() {
        String playerInput = console.getStringInput("Do you want to continue playing?");
        if(playerInput.equalsIgnoreCase("yes")){
            run();
        } else if (playerInput.equalsIgnoreCase("no")){
            remove(gambler.get(0));
        } else {
            while(!playerInput.equalsIgnoreCase("yes")||!playerInput.equalsIgnoreCase("no")){
                playerInput = console.getStringInput("Answer with a yes or no?");
                if(playerInput.equalsIgnoreCase("yes")){
                    run();
                }else if (playerInput.equalsIgnoreCase("no")) {
                    run();
                    remove(gambler.get(0));
                }
            }
        }

    }

    public void diceRoll() {
        Integer diceRoll = diceGenerator.diceRoll();
        if (diceRoll == 7 || diceRoll == 11){
            System.out.println("The dice number is: " + diceRoll);
            win();
            quit();
        } else if (diceRoll==2 || diceRoll==3 || diceRoll==4 || diceRoll==5 || diceRoll==6
                || diceRoll==8 || diceRoll==9 || diceRoll==10){
            System.out.println("The dice number is: " + diceRoll);
            lose();
            quit();
        }
    }

    public void run() {
        boolean game = true;
        noMoney();
        printBackroom();
        while(game) {
            Integer playerBet = console.getIntegerInput("Enter your bet, minimum bet $500\n" +
                    "Your balance is " + this.balance);
            setBet(playerBet);
            setRandomBets();
            String playerInput = console.getStringInput("Press 1 to get your roll");
            if(playerInput.equalsIgnoreCase("1")) {
                getPoolBets();
                poolBets(getPoolBets(), playerBet);
                System.out.println("Roll the dice");
                diceRoll();
            } else if (playerInput.equalsIgnoreCase("2")) {
                int playerChoice = console.getIntegerInput("Roll the dice ");
                int diceRoll = diceGenerator.diceRoll();
                if(playerChoice==diceRoll) {
                    win();
                }
            }
        }
        }


        public double gettingRobbedProbablity() {
        // For every roll, there is a probability of getting robbed
            double rob;
            while(robMechanicOn) {
                randomDiceRollRobbingProb = diceGenerator.diceRoll();
                if (randomDiceRollRobbingProb > 5) {
                    rob = this.balance * 0.5;
                    robMechanicOn = false;
                    return this.balance - rob;
                }
            }
        return 0.0;
        }

        public void printDice() {
        System.out.println("            _______.\n" +
                "   ______    | .   . |\\\n" +
                "  /     /\\   |   .   |.\\\n" +
                " /  '  /  \\  | .   . |.'|\n" +
                "/_____/. . \\ |_______|.'|\n" +
                "\\ . . \\    /  \\ ' .   \\'|\n" +
                " \\ . . \\  /    \\____'__\\|\n" +
                "  \\_____\\/");
        }

        public void printBackroom() {


        System.out.println("                                                                              \n" +
                "    //   ) )                                                                  \n" +
                "   //___/ /   ___      ___     / ___      __      ___      ___      _   __    \n" +
                "  / __  (   //   ) ) //   ) ) //\\ \\     //  ) ) //   ) ) //   ) ) // ) )  ) ) \n" +
                " //    ) ) //   / / //       //  \\ \\   //      //   / / //   / / // / /  / /  \n" +
                "//____/ / ((___( ( ((____   //    \\ \\ //      ((___/ / ((___/ / // / /  / /    ");
        }

    }










