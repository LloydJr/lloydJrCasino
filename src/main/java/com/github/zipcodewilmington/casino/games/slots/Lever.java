package com.github.zipcodewilmington.casino.games.slots;

import java.util.Random;

abstract class Lever {
    public static int[] PullLever() {
        Random random = new Random();
        int slot1, slot2, slot3;
        int[] pulled = new int[3];

        System.out.println("Pulling lever now\n" +
                "...\n" +
                "...\n" +
                "...\n");

        slot1 = random.nextInt(5) + 1;
        pulled[0] = slot1;
        slot2 = random.nextInt(5) + 1;
        pulled[1] = slot2;
        slot3 = random.nextInt(5) + 1;
        pulled[2] = slot3;

        System.out.println(slot1 + "  " + slot2 + "  " + slot3);
        return pulled;
    }
}
