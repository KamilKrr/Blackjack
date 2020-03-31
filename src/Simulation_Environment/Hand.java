package Simulation_Environment;

public class Hand {
    private Card[] myCards;
    private int numberOfCards;
    private int maxCards = 22;//maximum number of cards someone could have, only aces and hit on 21 (not realistic, only for crazy simulating)
    private double money = 0.0;//€
    private boolean blocked = false;//cannot act -> after spltting two aces
    private boolean hasDoubled = false;//true if player chose to double
    private boolean isBlackjack = false;//true if player wins with blackjack
    private boolean isSplitted = false;//true if hand was splitted

    public Hand(){
        myCards = new Card[maxCards];
        numberOfCards = 0;
    }

    public void acceptCard(Card card){
        myCards[numberOfCards++] = card;
    }

    public void removeLast() {
        numberOfCards--;
    }

    public Card getLastCard() {
        return myCards[1];
    }

    public boolean isDoubleCard() {
        return (numberOfCards == 2 && (myCards[0].getMyValue() == myCards[1].getMyValue()));
    }

    public boolean containsAce() {
        for (int i = 0; i < numberOfCards; i++) {
            if(myCards[i].getMyValue() == 1) {return true;}
        }
        return false;
    }

    public String displayHand(){
        String display = "";

        for (int i = 0; i < numberOfCards; i++) {
            display += myCards[i].displayCard();
            if(i < numberOfCards-1){
                display += ", ";
            }
        }

        return display;
    }

    public void reveal(){
        for (int i = 0; i < numberOfCards; i++) {
            myCards[i].setFaceUp(true);
        }
    }

    public void reset(){
        numberOfCards = 0;
    }

    public int getSum(){
        int sum = 0;
        int asses = 0;
        for (int i = 0; i < numberOfCards; i++) {
            if(myCards[i].isFaceUp()){

                int cardValue = myCards[i].getMyValue();
                if(cardValue == 1){
                    asses++;
                    sum++;
                }else{
                    sum += cardValue;
                }
            }
        }

        while(sum+10 <= 21 && asses > 0){
            asses--;
            sum+=10;
        }

        return sum;
    }

    public int getNumberOfCards(){
        return numberOfCards;
    }

    public double getMoney(){
        return money;
    }

    public void setMoney(double setMoney){
        money = setMoney;
    }

    public void block(){
        blocked = true;
    }

    public void unblock(){
        blocked = false;
    }

    public boolean isBlocked(){
        return blocked;
    }

    public void doubleDown(){
        hasDoubled = true;
    }

    public boolean hasDoubled(){
        return hasDoubled;
    }

    public boolean isBlackjack(){
        return isBlackjack;
    }

    public void blackjack(){
        isBlackjack = true;
    }

    public void split(){
        isSplitted = true;
    }

    public boolean isSplitted(){
        return isSplitted;
    }
}

