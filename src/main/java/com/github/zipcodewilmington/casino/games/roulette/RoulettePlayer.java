package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerPlayerInterface;

import java.util.ArrayList;
import java.util.List;

public class RoulettePlayer extends CasinoAccountManager implements GamblerPlayerInterface {
    List<CasinoAccount> holdPlayer = new ArrayList<>();
    Object casinoAccount;

    Integer balance;

    public RoulettePlayer(CasinoAccount casinoAccount) {
        this.casinoAccount = casinoAccount;
        this.balance = casinoAccount.getBalance();

    }



    @Override
    public CasinoAccount getArcadeAccount() {

        return (CasinoAccount) this.casinoAccount;
    }





}
