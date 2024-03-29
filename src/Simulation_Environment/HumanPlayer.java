package Simulation_Environment;

import Strategy_Engine.Observer;

import java.util.List;

public class HumanPlayer extends Player{

    InputProcessor inputProcessor;

    public HumanPlayer(double myBankroll){
        super(myBankroll, "Human");

        inputProcessor = new InputProcessor();
    }

    @Override
    public String nextAction(Observer o, List<String> moves, int dealerhand, int playerhand, boolean softHand, boolean doubleCard, int currentBet, int bankroll, int numberOfCards) {
        return inputProcessor.chooseAction();
    }

    @Override
    public double placeBet() {
        return inputProcessor.placeBet();
    }
}
