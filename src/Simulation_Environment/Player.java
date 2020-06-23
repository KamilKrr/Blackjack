package Simulation_Environment;

import Strategy_Engine.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected double bankroll;
    private double startingBankroll;
    private double bet;

    public int wins, loses, draws = 0;
    public double moneyWon, moneyLost = 0;

    public ArrayList<Hand> myHands = new ArrayList<>();

    private String name = "";

    private boolean isAuto;

    public abstract String nextAction(Observer o, List<String> moves, int dealerhand, int playerhand, boolean softHand, boolean doubleCard, int currentBet, int bankroll, int numberOfCards);
    public abstract double placeBet();

    public Player() {
        name = "Player#" + (int)Math.floor(Math.random()*100);
    }

    public Player(double myBankroll, String type) {
        name = type + "#" + (int)Math.floor(Math.random()*100);
        bankroll = myBankroll;
        startingBankroll = myBankroll;

    }

    public Player(double myCash, String myName, boolean myIsAuto) {
        bankroll = myCash;
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

    public void win(double difference){
        wins += 1;

        bankroll += difference;
        moneyWon += difference;
    }

    public void lose(double difference){
        loses += 1;

        bankroll -= difference;
        moneyLost += difference;
    }

    public void draw(){
        draws += 1;
    }

    public String stats(){
        return "wins: " + wins + ", loses: " + loses + ", draws: " + draws + ", % games won without draws: " + (double)wins / (wins+loses) * 100.0 + "% Starting Bankroll: " + startingBankroll + "€ vs Bankroll now: " + bankroll + "€";//, total money won: " + moneyWon + "€ vs lost: " + moneyLost + "€";
    }

    public boolean canPlaceBet(double tryThisBet){
        if(tryThisBet <= 0 || tryThisBet > bankroll){
            return false;
        }
        return true;
    }
    public void placeBet(double bet){
        this.bet = bet;
    }

    public double getBankroll(){
        return bankroll;
    }

    public double getStartingBankroll(){
        return startingBankroll;
    }

    public double getBet(){
        return bet;
    }

    public boolean isBancrupt(){
        return bankroll < 5;
    }
}
