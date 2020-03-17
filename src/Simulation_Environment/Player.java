package Simulation_Environment;

import java.util.ArrayList;

public class Player {

    private double cash;

    public ArrayList<Hand> myHands = new ArrayList<>();

    private String name = "";

    private boolean isAuto;

    public Player() {

    }

    public Player(double myCash, String myName, boolean myIsAuto) {
        cash = myCash;
        name = myName;
        isAuto = myIsAuto;
    }

    public void addHand(Hand hand) {
        myHands.add(hand);
    }
}
