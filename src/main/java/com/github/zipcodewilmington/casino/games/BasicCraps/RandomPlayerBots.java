package com.github.zipcodewilmington.casino.games.BasicCraps;

import java.util.Random;

public abstract class RandomPlayerBots {
    Integer randomBetsBot1;
    Integer randomBetsBot2;
    Integer randomBetsBot3;
    Integer randomBetsBot4;
    Integer randomBetsBot5;
    Integer randomBetsBot6;

    Integer playerBets;
    Integer pool7Or11Bets;
    Integer poolSetBets;
    Random rand = new Random();

    Integer poolAll7Or11Bets;
    Integer poolAllSetBets;



    public void setRandomBets(){
        this.randomBetsBot1 = rand.nextInt(2500 - 500) +1;
        this.randomBetsBot2 = rand.nextInt(2500 - 500) +1;
        this.randomBetsBot3 = rand.nextInt(2500 - 500) +1;
        this.randomBetsBot4 = rand.nextInt(2500 - 500) +1;
        this.randomBetsBot5 = rand.nextInt(2500 - 500) +1;
        this.randomBetsBot6 = rand.nextInt(2500 - 500) +1;

    }

    public Integer getPoolBets() {
        return this.randomBetsBot1 + this.randomBetsBot2 + this.randomBetsBot3;
    }

    public Integer getPoolSetBets() {
        return this.randomBetsBot4 + this.randomBetsBot5 + randomBetsBot6;
    }



    public Integer poolSetBets(Integer poolBets, Integer playerBet){
        this.playerBets = playerBet;
        this.poolSetBets = poolBets;
        this.poolAllSetBets = playerBet + poolBets;

        return this.poolAllSetBets;
    }

    public Integer poolBets(Integer bets, Integer playerBet){
        this.playerBets=playerBet;
        this.pool7Or11Bets = bets;
        this.poolAll7Or11Bets = playerBet + bets;

       return  this.poolAll7Or11Bets;
    }



}
