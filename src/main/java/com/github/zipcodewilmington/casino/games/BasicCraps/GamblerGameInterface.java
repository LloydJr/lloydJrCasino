package com.github.zipcodewilmington.casino.games.BasicCraps;

import com.github.zipcodewilmington.casino.PlayerInterface;

public interface GamblerGameInterface {

    void add(GamblerPlayerInterface player);

    void remove(GamblerPlayerInterface player);

    void run();



}
