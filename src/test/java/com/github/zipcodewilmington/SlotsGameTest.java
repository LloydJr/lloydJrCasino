package com.github.zipcodewilmington;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
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
    public void addPlayerTest() {
        //Given
        SlotsGame test = new SlotsGame();
        CasinoAccount testAccount = new CasinoAccount("Fred", "Bob", 5000);
        SlotsPlayer player = new SlotsPlayer(testAccount);
        //When
        test.add(player);
        //Then
        Assert.assertEquals(test.gambler.get(0).getArcadeAccount().getBalance(), testAccount.getBalance());
    }

    @Test
    public void getResultTest1() {
        //Given
        SlotsGame test = new SlotsGame();
        Random random = new Random(18);
        int slot1, slot2, slot3;
        int[] pulled = new int[3];

        slot1 = random.nextInt(3) + 1;
        pulled[0] = slot1;
        slot2 = random.nextInt(3) + 1;
        pulled[1] = slot2;
        slot3 = random.nextInt(3) + 1;
        pulled[2] = slot3;
        //When
        String expected = "bingo";
        String actual = test.getResult(pulled);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getResultTest2() {
        //Given
        SlotsGame test = new SlotsGame();
        Random random = new Random(1);
        int slot1, slot2, slot3;
        int[] pulled = new int[3];

        slot1 = random.nextInt(9) + 1;
        pulled[0] = slot1;
        slot2 = random.nextInt(9) + 1;
        pulled[1] = slot2;
        slot3 = random.nextInt(9) + 1;
        pulled[2] = slot3;
        //When
        String expected = "bango";
        String actual = test.getResult(pulled);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getResultTest3() {
        //Given
        SlotsGame test = new SlotsGame();
        Random random = new Random(3);
        int slot1, slot2, slot3;
        int[] pulled = new int[3];

        slot1 = random.nextInt(9) + 1;
        pulled[0] = slot1;
        slot2 = random.nextInt(9) + 1;
        pulled[1] = slot2;
        slot3 = random.nextInt(9) + 1;
        pulled[2] = slot3;
        //When
        String expected = "bongo";
        String actual = test.getResult(pulled);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void slotsTestLoss() {
        //Given
        SlotsGame test = new SlotsGame();
        CasinoAccount testAccount = new CasinoAccount("Fred", "Bob", 1);
        SlotsPlayer player = new SlotsPlayer(testAccount);
        Random random = new Random(3);
        int slot1, slot2, slot3;
        int[] pulled = new int[3];

        slot1 = random.nextInt(9) + 1;
        pulled[0] = slot1;
        slot2 = random.nextInt(9) + 1;
        pulled[1] = slot2;
        slot3 = random.nextInt(9) + 1;
        pulled[2] = slot3;

        String result = test.getResult(pulled);
        if (result.equals("bingo")) test.win(player, 25);
        if (result.equals("bango")) test.win(player, 10);
        if (result.equals("bongo")) test.lose(player);
        //When
        int actual = player.getArcadeAccount().getBalance() - 1;
        int expected = 0;
        //Then
        System.out.println(Arrays.toString(pulled));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void slotsTestPartialWin() {
        //Given
        SlotsGame test = new SlotsGame();
        CasinoAccount testAccount = new CasinoAccount("Fred", "Bob", 1);
        SlotsPlayer player = new SlotsPlayer(testAccount);
        Random random = new Random(1);
        int slot1, slot2, slot3;
        int[] pulled = new int[3];

        slot1 = random.nextInt(9) + 1;
        pulled[0] = slot1;
        slot2 = random.nextInt(9) + 1;
        pulled[1] = slot2;
        slot3 = random.nextInt(9) + 1;
        pulled[2] = slot3;

        String result = test.getResult(pulled);
        if (result.equals("bingo")) test.win(player, 25);
        if (result.equals("bango")) test.win(player, 10);
        if (result.equals("bongo")) test.lose(player);
        //When
        int actual = player.getArcadeAccount().getBalance() - 1;
        int expected = 10;
        //Then
        System.out.println(Arrays.toString(pulled));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void slotsTestJackpot() {
        //Given
        SlotsGame test = new SlotsGame();
        CasinoAccount testAccount = new CasinoAccount("Fred", "Bob", 1);
        SlotsPlayer player = new SlotsPlayer(testAccount);
        Random random = new Random(18);
        int slot1, slot2, slot3;
        int[] pulled = new int[3];

        slot1 = random.nextInt(3) + 1;
        pulled[0] = slot1;
        slot2 = random.nextInt(3) + 1;
        pulled[1] = slot2;
        slot3 = random.nextInt(3) + 1;
        pulled[2] = slot3;

        String result = test.getResult(pulled);
        if (result.equals("bingo")) test.win(player, 25);
        if (result.equals("bango")) test.win(player, 10);
        if (result.equals("bongo")) test.lose(player);
        //When
        int actual = player.getArcadeAccount().getBalance() - 1;
        int expected = 25;
        //Then
        System.out.println(Arrays.toString(pulled));
        Assert.assertEquals(expected, actual);
    }
}
