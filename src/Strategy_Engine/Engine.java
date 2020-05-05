package Strategy_Engine;


import java.util.HashMap;
import java.util.Map;

public class Engine {
    public static HashMap<Integer, Double> dealer = new HashMap<>();

    public static String bestMove(Shoe shoe, int dealerCard, int playerSum, boolean isPlayerSoftHand) {
        for (int i = 17; i <= 22; i++) {
            dealer.put(i, 0.0);
        }

        HashMap<Integer, Double> probs = dealerProbabilities(shoe, dealerCard, 1.0, dealer, isPlayerSoftHand);

        System.out.println(probs);
        double p = 0;
        for (Double value: probs.values()){
            p+= value;

        }

        System.out.println(p);

        return "hit";
    }

    public static HashMap<Integer, Double> dealerProbabilities(Shoe shoe, int dealerCard, double probability, HashMap<Integer, Double> probabilities, boolean softHand) {
        //exit condition
        if(probability == 0) return probabilities;

        if(!softHand) {
            if(dealerCard > 10) {
                for (int i = dealerCard + 1; i <= 16; i++) {
                    Shoe s = new Shoe(shoe);
                    s.remove(i - dealerCard);
                    dealerProbabilities(s, i, shoe.count(i - dealerCard)*1.0 / shoe.getCards().size() * probability, probabilities, false);
                }
                for (int i = 17; i <= 21; i++) {
                    probabilities.put(i, shoe.count(i - dealerCard)*1.0 / shoe.getCards().size() * probability + probabilities.get(i));
                }
                probabilities.put(22, shoe.countEqualsOrHigher(22 - dealerCard)*1.0 / shoe.getCards().size() * probability + probabilities.get(22));
            } else {
                for (int i = dealerCard + 2; i <= 16 && i <= dealerCard + 11; i++) {
                    Shoe s = new Shoe(shoe);
                    if (i != dealerCard + 11) {
                        s.remove(i - dealerCard);

                        dealerProbabilities(s, i, shoe.count(i - dealerCard)*1.0 / shoe.getCards().size() * probability, probabilities, false);
                    } else {
                        s.remove(1);
                        dealerProbabilities(s, i, shoe.count(1)*1.0 / shoe.getCards().size() * probability, probabilities, true);
                    }
                }
                for (int i = 17; i <= 21; i++) {
                    if (i != dealerCard + 11) {
                        probabilities.put(i, shoe.count(i - dealerCard)*1.0 / shoe.getCards().size() * probability + probabilities.get(i));
                    } else {
                        probabilities.put(i, shoe.count(1)*1.0 / shoe.getCards().size() * probability + probabilities.get(i));
                    }
                }
            }
        } else {
            for (int i = dealerCard + 1; i <= 16; i++) {
                Shoe s = new Shoe(shoe);
                s.remove(i - dealerCard);
                dealerProbabilities(s, i, shoe.count(i - dealerCard)*1.0 / shoe.getCards().size() * probability, probabilities, true);
            }
            for (int i = 17; i <= 21; i++) {
                probabilities.put(i, shoe.count(i - dealerCard)*1.0 / shoe.getCards().size() * probability + probabilities.get(i));
            }
            for (int i = 22; i <= dealerCard + 10; i++) {
                Shoe s = new Shoe(shoe);
                s.remove(i - dealerCard);
                dealerProbabilities(s, i-10, shoe.count(i - dealerCard)*1.0 / shoe.getCards().size() * probability, probabilities, false);
            }
        }

        return probabilities;
    }
}