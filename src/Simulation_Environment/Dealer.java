package Simulation_Environment;

import java.util.ArrayList;

public class Dealer {
    private double cash = 0;

    private Hand myHand;

    private String name = "";

    public Dealer() {
        this("");
    }

    public Dealer(String myName) {
        name = name;
    }

    public void newHand(){
        myHand = new Hand();
    }

    public void acceptCard(Card card){
        myHand.acceptCard(card);
    }

    public Hand getHand(){
        return myHand;
    }
}
