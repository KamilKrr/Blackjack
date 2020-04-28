package Simulation_Environment;

import Strategy_Engine.Strategy_Engine;

public class StartegyEnginePlayer extends Player{

    public StartegyEnginePlayer(double myBankroll){
        super(myBankroll, "StartegyEnginePlayer");
    }

    @Override
    public String nextAction(int dealerhand, int playerhand, boolean softHand, boolean doubleCard, int currentBet, int bankroll, int numberOfCards) {

        return "";
    }

    @Override
    public double placeBet() {
        return 0;
    }
}
