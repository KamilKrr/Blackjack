package Simulation_Environment;

public class Hand {
    private Card[] myCards;
    private int numberOfCards;
    private int maxCards = 10;

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



    public boolean doubleCard() {
        return (myCards[0].getMyValue() == myCards[1].getMyValue());
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
}

