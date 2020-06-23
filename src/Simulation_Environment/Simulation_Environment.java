package Simulation_Environment;

import Strategy_Engine.Observer;

import java.util.ArrayList;

public class Simulation_Environment {

    public Simulation_Environment(int rounds, int humanPlayerCount, int basicStrategyPlyerCount, int strategyEnginePlayerCount){
        //TODO: bot naming, better statistics (String.format), ask insurance before round, cash, statistics for each hand
        long startTime = System.currentTimeMillis();


        Blackjack bj = new Blackjack(humanPlayerCount, basicStrategyPlyerCount, strategyEnginePlayerCount);

        for (int i = 0; i < rounds; i++) {
            bj.newRound();
        }
        bj.printStats();


        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime + "millis");


        //test
        /*
        Shoe shoe = new Shoe(8, 50);
        InputProcessor inputProcessor = new InputProcessor();
        Observer observer = new Observer(8);
        ArrayList<String> moves = new ArrayList<>();
        moves.add("hit");
        moves.add("stand");
        moves.add("double");
        for (int i = 2; i < 12; i++) {
            for (int j = 2; j <= 10; j++) {
                for (int k = 2; k <= 10; k++) {
                    int dealerSum = i;
                    int playerSum = j;

                    if(j == k){
                        moves.add("split");
                    }

                    String basicSTrategyAction = inputProcessor.normalize(new BasicStrategyPlayer(1000).nextAction(observer, moves, dealerSum, (j+k), false, (j == k ? true : false), 1, 100, 2));
                    String engineAction = inputProcessor.normalize(new StrategyEnginePlayer(1000).nextAction(observer, moves, dealerSum, (j+k), false, (j == k ? true : false), 1, 100, 2));

                    if (!basicSTrategyAction.equals(engineAction)) {
                        System.out.println(basicSTrategyAction);
                        System.out.println(engineAction);
                        System.out.println("dealer " + i);
                        System.out.println("player " + (j+k) + " k:" + k + " j:" + j);
                        System.out.println("isdouble: " + (j == k ? true : false));
                        System.out.println("___________");
                    }

                    if(j == k){
                        moves.remove("split");
                    }
                }
            }

        }
        */

    }

}
