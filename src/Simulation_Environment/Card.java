package Simulation_Environment;

public class Card {
    private String myRank;
    private int myValue;
    private boolean isFaceUp;

    public Card(String rank, int value)
    {
        myRank = rank;
        myValue = value;
        isFaceUp = false;
    }

    public void setFaceUp(boolean faceUpValue)
    {
        isFaceUp = faceUpValue;
    }

    public String displayCard(){
        if(isFaceUp){
            return myRank;
        }
        return "***";
    }

    @Override
    public String toString() {
        return myRank;
    }

    public int getMyValue() {
        return myValue;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public boolean isAce(){
        return (myValue == 1);
    }
}

