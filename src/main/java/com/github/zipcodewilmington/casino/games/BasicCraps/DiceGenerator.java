package com.github.zipcodewilmington.casino.games.BasicCraps;

import java.util.Random;

public class DiceGenerator {
    Random rand = new Random();

    public Integer diceRoll(){
        Integer x = rand.nextInt(6 - 1) + 1 ;
        Integer y = rand.nextInt(6 - 1) + 1;
        return x + y;
    }





}
