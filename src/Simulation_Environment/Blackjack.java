package Simulation_Environment;

public class Blackjack {
    private InputProcessor ip;

    private Deck deck;

    private Hand dealer;

    private Hand[] players;

    public Blackjack(int players){
        newGame();
    }

    public void newGame(){
        deck = new Deck();
    }

}
