package Strategy_Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {
    private ArrayList<Integer> cards = new ArrayList<>();

    public void addAll(List<Integer> extraCards){
        cards.addAll(extraCards);
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    public void remove(int card){
        //remove element not index
        cards.remove(Integer.valueOf(card));
    }

    public int count(int card){
        return Collections.frequency(cards, card);
    }
}
