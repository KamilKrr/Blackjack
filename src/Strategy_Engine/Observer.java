package Strategy_Engine;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    private int decksInShoe;

    private List<Integer> deck = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10);
    private Shoe shoe;
    private ArrayList<Integer> myHand = new ArrayList<>();
    private int dealer;


    public Observer(int decksInShoe){
        shoe = new Shoe();
        this.decksInShoe = decksInShoe;

        for (int i = 0; i < decksInShoe; i++) {
            shoe.addAll(deck);
        }

        System.out.println(shoe.getCards());
        System.out.println(shoe.count(1));
    }


    public ArrayList<Integer> getShoe(){
        return shoe.getCards();
    }

    private void removeCard(int card){
        shoe.remove(card);
    }

    public void observeCard(int card){
        removeCard(card);
    }
}
