package Strategy_Engine;


import java.util.HashMap;
import java.util.Map;

public class Engine {
    public static HashMap<Integer, Double> dealer = new HashMap<>();

    public static String bestMove(Shoe shoe, int dealerCard, int playerSum, boolean isPlayerSoftHand) {
        for (int i = 17; i <= 22; i++) {
            dealer.put(i, 0.0);
        }

        return "hit";
    }

    public static HashMap<Integer, Double> dealerProbabilities(Shoe shoe, int dealerCard, double probability, HashMap<Integer, Double> probabilities, boolean softHand) {

        if(!softHand) {
            //TODO: Kann nicht 1 dazu bekommen wenn DealerHand <= 10 ist
            for (int i = dealerCard + 1; i <= 16 && i <= dealerCard + 11; i++) {
                Shoe s = new Shoe(shoe);
                s.remove(i - dealerCard);
                if (i != dealerCard + 11) {
                    dealerProbabilities(s, i, shoe.count(i - dealerCard) / shoe.getCards().size() * 100 * probability, probabilities, false);
                } else {
                    dealerProbabilities(s, i, shoe.count(i - dealerCard) / shoe.getCards().size() * 100 * probability, probabilities, true);
                }
            }
            for (int i = 17; i <= 21; i++) {
                probabilities.put(i, shoe.count(i - dealerCard) / shoe.getCards().size() * 100 * probability + probabilities.get(i));
            }
            probabilities.put(22, shoe.countEqualsOrHigher(22 - dealerCard) / shoe.getCards().size() * 100 * probability + probabilities.get(22));
        } else {
            //TODO: Beide for Schleifen nochmal überprüfen ob sie auf soft hands zutreffen
            for (int i = dealerCard + 1; i <= 16 && i <= dealerCard + 10; i++) {
                Shoe s = new Shoe(shoe);
                s.remove(i - dealerCard);
                dealerProbabilities(s, i, shoe.count(i - dealerCard) / shoe.getCards().size() * 100 * probability, probabilities, true);
            }
            for (int i = 17; i <= 21; i++) {
                probabilities.put(i, shoe.count(i - dealerCard) / shoe.getCards().size() * 100 * probability + probabilities.get(i));
            }
            //TODO: Methode wird als hard hand aufgerufen wenn man über 21 hinaus geht
        }

        return probabilities;
    }
}