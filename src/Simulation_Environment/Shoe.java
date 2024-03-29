package Simulation_Environment;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Shoe {

    private int percentToReshuffle = 70;
    private int nextCardPosition;
    private int decks = 1;

    Card[] shoe;

    public Shoe(int decks, int percentToReshuffle){
        this.decks = decks;
        this.percentToReshuffle = percentToReshuffle;

        assembleNewShoe();
    }

    public void assembleNewShoe(){
        nextCardPosition = 0;
        shoe = new Card[52*decks];

        for (int i = 0; i < decks; i++) {
            Card[] cards = new Deck().getCrads();
            for (int j = 0; j < cards.length; j++) {
                shoe[i*52 + j] = cards[j];
            }
        }
        shuffle();
    }

    public boolean usedOver(int percent){
        return (nextCardPosition > shoe.length*percent/100);
    }

    public Card dealOne(boolean faceUpValue){
        if (nextCardPosition > -1 && nextCardPosition < shoe.length) {
            shoe[nextCardPosition].setFaceUp(faceUpValue);
            return shoe[nextCardPosition++];
        }
        System.out.println("No Cards left");
        return null;
    }

    public Card[] getCards(){
        return Arrays.copyOfRange(shoe, nextCardPosition, shoe.length);
    }

    public void shuffle(){
        Random rnd = ThreadLocalRandom.current();
        for (int i = shoe.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = shoe[index];
            shoe[index] = shoe[i];
            shoe[i] = a;
        }
    }

    //only for debugging
    public void display(){
        String cards = "";
        for (int i = 0; i <shoe.length; i++) {
            cards += shoe[i].getMyValue() + ", ";
        }

        System.out.println(cards + " | length: " + shoe.length);
    }
}
