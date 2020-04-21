package Strategy_Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {

    public Shoe(Shoe anotherShoe){
        ArrayList<Integer> clone = new ArrayList<>();
        for (Integer card : anotherShoe.cards) clone.add(card.intValue());
        this.cards = clone;
    }

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

    public int countEqualsOrHigher(int card){
        int count = 0;
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i) >= card){
                count++;
            }
        }
        return count;
    }
}
