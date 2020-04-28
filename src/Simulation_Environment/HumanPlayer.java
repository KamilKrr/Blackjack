package Simulation_Environment;

public class HumanPlayer extends Player{

    InputProcessor inputProcessor;

    public HumanPlayer(double myBankroll){
        super(myBankroll, "Human");

        inputProcessor = new InputProcessor();
    }

    @Override
    public String nextAction(int dealerhand, int playerhand, boolean softHand, boolean doubleCard, int currentBet, int bankroll, int numberOfCards) {
        return inputProcessor.chooseAction();
    }

    @Override
    public double placeBet() {
        return inputProcessor.placeBet();
    }
}
