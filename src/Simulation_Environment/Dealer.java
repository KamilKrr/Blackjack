package Simulation_Environment;


public class Dealer {
    public int passesOn = 17;

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

    public void reveal(){
        myHand.reveal();
    }

    public int getSum(){
        return myHand.getSum();
    }

    public String displayHand(){
        return myHand.displayHand();
    }

    public boolean isAce(){
        return (getSum() == 1);
    }

}
