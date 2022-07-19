package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class WarGamePlayer extends CasinoAccountManager implements PlayerInterface {

    Object casinoAccount;

    Integer balance;

    public WarGamePlayer (CasinoAccount casinoAccount) {
        this.casinoAccount = casinoAccount;
        this.balance = casinoAccount.getBalance();

    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return (CasinoAccount) this.casinoAccount;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

}
