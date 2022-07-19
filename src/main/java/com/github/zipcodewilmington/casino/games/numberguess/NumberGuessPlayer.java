package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.PlayerInterface;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by leon on 7/21/2020.
 */

public class NumberGuessPlayer extends CasinoAccountManager implements PlayerInterface {
    List<CasinoAccount> holdPlayer = new ArrayList<>();
    Object casinoAccount;
    Integer balance;

    public NumberGuessPlayer(CasinoAccount casinoAccount){
//        List<CasinoAccount> NumberGuessPlayer = new List<CasinoAccount>();
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