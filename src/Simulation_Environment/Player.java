package Simulation_Environment;

import java.util.ArrayList;

public class Player {

    private double cash;

    public int wins, loses, draws = 0;

    public ArrayList<Hand> myHands = new ArrayList<>();

    private String name = "";

    private boolean isAuto;

    public Player() {
        name = "Player#" + (int)Math.floor(Math.random()*100);
    }

    public Player(boolean myIsAuto) {
        String type = myIsAuto ? "Bot" : "Player";
        name = type + "#" + (int)Math.floor(Math.random()*100);
        isAuto = myIsAuto;
    }

    public Player(double myCash, String myName, boolean myIsAuto) {
        cash = myCash;
        name = myName;
        isAuto = myIsAuto;
    }

    public void removeHands(){
        myHands.clear();
    }

    public void addHand(Hand hand) {
        myHands.add(hand);
    }

    public String getName(){
        return name;
    }

    public boolean isAuto(){
        return isAuto;
    }



    public void win(){
        wins += 1;
    }

    public void lose(){
        loses += 1;
    }

    public void draw(){
        draws += 1;
    }

    public String stats(){
        return "wins: " + wins + ", loses: " + loses + ", draws: " + draws + ", % games won without draws: " + (double)wins / (wins+loses) * 100.0 + "%";
    }
}
