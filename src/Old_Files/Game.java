package Andere.Eigene.Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Game {

    private static List<List<Integer>> players = new ArrayList<>();
    //private static Map<Integer, Integer> cards = new TreeMap<>();
    private static List<Integer> cards = createCards(6);

    public static void main(String[] args) {


        List<Integer> player = new ArrayList<>();
        List<Integer> dealer = new ArrayList<>();
        players.add(player);
        players.add(dealer);
        System.out.println(cards.toString());



    }



    public static List<Integer> createCards (int decks) {
        List<Integer> cards = new ArrayList<>();
        for (int i = 1; i <= decks*4; i++) {
            for (int j = 1; j <= 13; j++) {
                if (j >= 10) {
                    cards.add(10);
                } else{
                    cards.add(j);
                }
            }
        }
        return cards;
    }
    /*public static Map<Integer, Integer> createCards (int decks) {
        Map<Integer, Integer> cards = new TreeMap<>();
            for (int i = 1; i <= 9; i++) {
                cards.put(i, decks*4);
            }
        cards.put(10, 16);
        return cards;
    }*/



    public static List<Integer> initialDeal (List<Integer> cards) {
        return cards;
    }



    public static List<Integer> deal (int player, List<Integer> cards) {
        int card = (int)(Math.random() * cards.size() + 1);
        cards.remove(card);
        players.get(player);
        return cards;
    }
}
