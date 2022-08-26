package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.BasicCraps.GamblerPlayerInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsPlayer extends CasinoAccountManager implements GamblerPlayerInterface {

    Object casinoAccount;

    Integer balance;

    public SlotsPlayer(CasinoAccount casinoAccount) {
        this.casinoAccount = casinoAccount;
         this.balance = casinoAccount.getBalance();

    }



    @Override
    public CasinoAccount getArcadeAccount() {

        return (CasinoAccount) this.casinoAccount;
    }




}