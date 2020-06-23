package Simulation_Environment;

import Strategy_Engine.Strategy_Engine;

import Strategy_Engine.Observer;

import java.util.List;

public class StrategyEnginePlayer extends Player{

    public StrategyEnginePlayer(double myBankroll){
        super(myBankroll, "StartegyEnginePlayer");
    }

    @Override
    public String nextAction(Observer o, List<String> moves, int dealerhand, int playerhand, boolean softHand, boolean doubleCard, int currentBet, int bankroll, int numberOfCards) {
        return Strategy_Engine.bestMove(o, dealerhand, playerhand, softHand, moves);
    }

    @Override
    public double placeBet() {
        return 1;
    }
}
