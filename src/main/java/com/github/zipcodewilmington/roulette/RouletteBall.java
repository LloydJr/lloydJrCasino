package com.github.zipcodewilmington.casino.games.roulette;

import java.util.Random;

public class RouletteBall{

    private int number;
    private String color;
    private boolean even;

    RouletteBall(int number, String color, boolean even){
        this.number = number;
        this.color = color;
        this.even = even;
    }

    public RouletteBall() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEven() {
        return even;
    }

    public void setEven(boolean even) {
        this.even = even;
    }

}
