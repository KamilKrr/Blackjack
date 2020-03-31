package Simulation_Environment;

public class Simulation_Environment {

    public static void main(String[] args) {

        //TODO: bot naming, better statistics (String.format), ask insurance before round, cash, statistics for each hand
        long startTime = System.currentTimeMillis();


        Blackjack bj = new Blackjack(0, 10);

        for (int i = 0; i < 10000000; i++) {
            bj.newRound();
        }
        bj.printStats();


        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime + "millis");
    }
}
