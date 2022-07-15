package com.github.zipcodewilmington;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class SlotsGameTest {

    @Test
    public void randomNumber() {
        Random rand = new Random(1);
        int expected = rand.nextInt(10);
        int actual = 5;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void pullLeverTest() {

    }
}
