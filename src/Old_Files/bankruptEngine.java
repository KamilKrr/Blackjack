package Andere.Eigene.Blackjack;

import java.util.Arrays;

public class bankruptEngine {

    public static void main(String[] args) {
        printPossibility(1000000, 100, 110, 10);
    }


    public static int playBlackJack(int games, double quote, int startbudget) {
        int bankruptOn = games;
        for (int i = 0; i < games; i++) {
            if(startbudget == 0) {
                startbudget = -1;
                bankruptOn = i-1;
            }
            if(startbudget > 0) {
                double rnd = Math.random();
                if(rnd > quote/200) {
                    startbudget -= 1;
                } else {
                    startbudget += 1;
                }
            }
        }
        return bankruptOn;
    }


    public static int[] playMutlipleBlackJack(int times, int games, double quote, int startbudget) {
        int[] results = new int[games+1];
        for (int i = 0; i < times; i++) {
            results[playBlackJack(games, quote, startbudget)]++;
        }
        return results;
    }

    public static void printPossibility(int times, int games, double quote, int startbudget) {
        int[] array = playMutlipleBlackJack(times, games, quote, startbudget);
        for (int i = 0; i < array.length - 1; i++) {
            System.out.println("game " + (i+1) + ": " + array[i] + " out of " + times + ": " + array[i]*1.0/times*100.0 + "%");
        }
        System.out.println("chance of not going bankrupt: " + array[array.length-1]*1.0/times*100.0 + "% (" + array[array.length-1] + " out of " + times + " times)");
    }

}
