package com.example.user.hungrysnake;

public class SnakeControlClass {
    static int x[] = new int[1000];
    static int y[] = new int[1000];
    static int xp[] = new int[1000];
    static int yp[] = new int[1000];

    FairClass obj;

    public static void moveUpFromLeft() {
        xp[0] = x[0];
        yp[0] = y[0];
        y[0] = y[0] - FairClass.snakeBodyWidth;

        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];


        }
    }

    public static void moveDownFromLeft() {
        xp[0] = x[0];
        yp[0] = y[0];
        y[0] = y[0] + FairClass.snakeBodyWidth;

        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];


        }
    }

    public static void moveLeftFromUp() {
        xp[0] = x[0];
        yp[0] = y[0];
        x[0] = x[0] - FairClass.snakeBodyWidth;

        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];
        }


    }

    public static void moveRightFromUp() {
        xp[0] = x[0];
        yp[0] = y[0];
        x[0] = x[0] + FairClass.snakeBodyWidth;

        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];
        }
    }

    public static void moveUpFromRight() {
        xp[0] = x[0];
        yp[0] = y[0];
        y[0] = y[0] - FairClass.snakeBodyWidth;

        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];
        }
    }

    public static void moveDownFromRight() {
        xp[0] = x[0];
        yp[0] = y[0];
        y[0] = y[0] + FairClass.snakeBodyWidth;

        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];
        }
    }

    public static void moveLeftFromDown() {
        xp[0] = x[0];
        yp[0] = y[0];
        x[0] = x[0] - FairClass.snakeBodyWidth;


        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];
        }
    }

    public static void moveRightFromDown() {
        xp[0] = x[0];
        yp[0] = y[0];
        x[0] = x[0] + FairClass.snakeBodyWidth;


        for (int i = 1; i <= FairClass.snakeLenght; i++) {
            xp[i] = x[i];
            yp[i] = y[i];
            x[i] = xp[i - 1];
            y[i] = yp[i - 1];
        }
    }

}
