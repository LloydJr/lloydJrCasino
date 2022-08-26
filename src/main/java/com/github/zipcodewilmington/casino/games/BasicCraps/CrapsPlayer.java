package com.github.zipcodewilmington.casino.games.BasicCraps;

import com.github.zipcodewilmington.casino.CasinoAccount;

import java.util.ArrayList;
import java.util.List;

public class CrapsPlayer implements GamblerPlayerInterface{

    Object casinoAccount;
    Integer balance;

    public CrapsPlayer(CasinoAccount casinoAccount) {
        this.casinoAccount = casinoAccount;
        this.balance = casinoAccount.getBalance();

    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return (CasinoAccount) this.casinoAccount;
    }


}
