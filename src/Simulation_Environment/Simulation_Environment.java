package Simulation_Environment;

public class Simulation_Environment {

    public Simulation_Environment(){
        //TODO: bot naming, better statistics (String.format), ask insurance before round, cash, statistics for each hand
        long startTime = System.currentTimeMillis();

        int rounds = 1;


        Blackjack bj = new Blackjack(0, 2);

        for (int i = 0; i < rounds; i++) {
            bj.newRound();
        }
        bj.printStats();


        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime + "millis");
    }

}
