package Strategy_Engine;

import Simulation_Environment.Card;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    private int decksInShoe;

    private List<Integer> deck = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10);
    private Shoe shoe;
    private ArrayList<Integer> myHand = new ArrayList<>();
    private int dealer;

    private int cardSetsInDeck = 4;


    public Observer(int decksInShoe){
        shoe = new Shoe();
        this.decksInShoe = decksInShoe;

        for (int i = 0; i < decksInShoe; i++) {
            for (int j = 0; j < cardSetsInDeck; j++) {
                shoe.addAll(deck);
            }
        }

        /*
        System.out.println(shoe.getCards());
        System.out.println(shoe.count(1));
        System.out.println(shoe.count(10));
        System.out.println(shoe.count(9));
        System.out.println(shoe.countEqualsOrHigher(9));
        System.out.println(shoe.countEqualsOrHigher(1));
        shoe.remove(21);
        System.out.println(shoe.countEqualsOrHigher(1));
        /**/

    }

    public void restoreAllCards(){
        shoe.removeAll();
        for (int i = 0; i < this.decksInShoe; i++) {
            for (int j = 0; j < cardSetsInDeck; j++) {
                shoe.addAll(deck);
            }
        }
    }

    public Shoe getShoe(){
        return shoe;
    }

    public void removeCard(int card){
        shoe.remove(card);
    }

    public void observeCard(int card){
        removeCard(card);
    }


    public void observeCard(Card card){
        removeCard(card.getMyValue());
    }

    public void display(){
        System.out.println(shoe.getCards() + " | length: " + shoe.getCards().size());
    }
}
