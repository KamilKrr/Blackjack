package Strategy_Engine;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    private int decksInShoe;

    private List<Integer> deck = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10);
    private ArrayList<Integer> shoe = new ArrayList<>();
    private ArrayList<Integer> myHand = new ArrayList<>();
    private int dealer;


    public Observer(int decksInShoe){
        this.decksInShoe = decksInShoe;

        for (int i = 0; i < decksInShoe; i++) {
            shoe.addAll(deck);
        }

        System.out.println(shoe);
    }


    public ArrayList<Integer> getShoe(){
        return shoe;
    }

    private void removeCard(int card){
        //remove element not idnex
        shoe.remove(Integer.valueOf(card));
    }
}
