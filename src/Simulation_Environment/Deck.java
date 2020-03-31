package Simulation_Environment;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private static final String  RANKS[] = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    private static final int[] VALUES = {1,2,3,4,5,6,7,8,9,10,10,10,10};

    private int nextCardPosition;

    private Card[] deck;

    //Default Constructor when number of cards not specified
    //52 Cards default
    public Deck(){
        this(52);
    }

    public Deck(int cardsInDeck) {
        deck = new Card[cardsInDeck];

        for (int i = 0; i < deck.length; i++) {
            deck[i] = new Card(RANKS[i%13], VALUES[i%13]);
        }

        shuffle();
        nextCardPosition = 0;
    }

    public void shuffle(){
        Random rnd = ThreadLocalRandom.current();
        for (int i = deck.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = deck[index];
            deck[index] = deck[i];
            deck[i] = a;
        }
    }

    public Card[] getCrads(){
        return deck;
    }
}
