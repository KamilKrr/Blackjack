package Strategy_Engine;


import java.util.*;

public class Engine {
    public static HashMap<Integer, Double> dealer = new HashMap<>();

    public static String bestMove(Shoe shoe, int dealerCard, int playerSum, boolean isPlayerSoftHand, List<String> moves) {
        for (int i = 17; i <= 22; i++) {
            dealer.put(i, 0.0);
        }
        HashMap<Integer, Double> probabilities = dealerProbabilities(shoe, dealerCard, 1.0, dealer, isPlayerSoftHand);
        System.out.println(probabilities);

        if (moves.contains("insurance")) {
            if(insuranceEV(shoe) > 0) { return "insurance"; }
            return "no insurance";
        }

        double h = playerHitEV(shoe, probabilities, playerSum, isPlayerSoftHand);
        double s = playerStandEV(probabilities, playerSum);
        int count = 2;
        double d = 0;
        double p = 0;
        if (moves.contains("double")) { d = playerDoubleEV(shoe, probabilities, playerSum, isPlayerSoftHand); count++; }
        if (moves.contains("split")) { p = playerSplitEV(shoe, probabilities, playerSum, isPlayerSoftHand); count++; }

        if ((count == 2 && h > s) || (count == 3 && h > s && h > d) || (count == 4 && h > s && h > d && h > p )) { return "hit"; }
        if ((count == 2 && s > h) || (count == 3 && s > h && s > d) || (count == 4 && s > h && s > d && s > p )) { return "split"; }
        if ((count == 3 && d > h && d > s) || (count == 4 && d > h && d > s && d > p )) { return "double"; }
        if (count == 4 && p > h && p > s && p > d) { return "split"; }

        return "error";
    }

    public static HashMap<Integer, Double> dealerProbabilities(Shoe shoe, int dealerCard, double probability, HashMap<Integer, Double> probabilities, boolean softHand) {
        //exit condition
        if (probability == 0) return probabilities;

        if (!softHand) {
            if (dealerCard > 10) {
                for (int i = dealerCard + 1; i <= 16; i++) {
                    Shoe s = new Shoe(shoe);
                    s.remove(i - dealerCard);
                    dealerProbabilities(s, i, shoe.count(i - dealerCard) * 1.0 / shoe.getCards().size() * probability, probabilities, false);
                }
                for (int i = 17; i <= 21; i++) {
                    probabilities.put(i, shoe.count(i - dealerCard) * 1.0 / shoe.getCards().size() * probability + probabilities.get(i));
                }
                probabilities.put(22, shoe.countEqualsOrHigher(22 - dealerCard) * 1.0 / shoe.getCards().size() * probability + probabilities.get(22));
            } else {
                for (int i = dealerCard + 2; i <= 16 && i <= dealerCard + 11; i++) {
                    Shoe s = new Shoe(shoe);
                    if (i != dealerCard + 11) {
                        s.remove(i - dealerCard);

                        dealerProbabilities(s, i, shoe.count(i - dealerCard) * 1.0 / shoe.getCards().size() * probability, probabilities, false);
                    } else {
                        s.remove(1);
                        dealerProbabilities(s, i, shoe.count(1) * 1.0 / shoe.getCards().size() * probability, probabilities, true);
                    }
                }
                for (int i = 17; i <= 21; i++) {
                    if (i != dealerCard + 11) {
                        probabilities.put(i, shoe.count(i - dealerCard) * 1.0 / shoe.getCards().size() * probability + probabilities.get(i));
                    } else {
                        probabilities.put(i, shoe.count(1) * 1.0 / shoe.getCards().size() * probability + probabilities.get(i));
                    }
                }
            }
        } else {
            for (int i = dealerCard + 1; i <= 16; i++) {
                Shoe s = new Shoe(shoe);
                s.remove(i - dealerCard);
                dealerProbabilities(s, i, shoe.count(i - dealerCard) * 1.0 / shoe.getCards().size() * probability, probabilities, true);
            }
            for (int i = 17; i <= 21; i++) {
                probabilities.put(i, shoe.count(i - dealerCard) * 1.0 / shoe.getCards().size() * probability + probabilities.get(i));
            }
            for (int i = 22; i <= dealerCard + 10; i++) {
                Shoe s = new Shoe(shoe);
                s.remove(i - dealerCard);
                dealerProbabilities(s, i - 10, shoe.count(i - dealerCard) * 1.0 / shoe.getCards().size() * probability, probabilities, false);
            }
        }

        return probabilities;
    }

    public static double playerStandEV(HashMap<Integer, Double> probabilities, int playerSum) {
        double ev = 0;

        for (int i = 17; i <= 21; i++) {
            if (playerSum > i) {
                ev += probabilities.get(i);
            } else if (playerSum < i) {
                ev -= probabilities.get(i);
            }
        }
        ev += probabilities.get(22);

        return ev;
    }

    public static double playerDoubleEV(Shoe shoe, HashMap<Integer, Double> probabilities, int playerSum, boolean softHand) {
        double ev = 0;

        for (int i = playerSum + 1; i <= playerSum + 10; i++) {
            if (i - playerSum != 1 || i >= 11) {
                if (i >= 22 && !softHand) {
                    ev -= shoe.count(i - playerSum) * 2.0 / shoe.getCards().size();
                } else if (i >= 22) {
                    ev += shoe.count(i - playerSum) * 2.0 / shoe.getCards().size() * playerStandEV(probabilities, i - 10);
                } else {
                    ev += shoe.count(i - playerSum) * 2.0 / shoe.getCards().size() * playerStandEV(probabilities, i);
                }
            } else {
                ev += shoe.count(1) * 2.0 / shoe.getCards().size() * playerStandEV(probabilities, i + 10);
            }
        }

        return ev;
    }

    public static double playerHitEV(Shoe shoe, HashMap<Integer, Double> probabilities, int playerSum, boolean softHand) {
        double ev = 0;
        //Hit ev = Hit ev oder Stand ev (je nach dem welches größer ist) mit jeder Karte
        return ev;
    }

    public static double playerSplitEV(Shoe shoe, HashMap<Integer, Double> probabilities, int playerSum, boolean softHand) {
        if(softHand) { return playerDoubleEV(shoe, probabilities, 11, true); }
        return 2.0 * playerHitEV(shoe, probabilities, playerSum/2, false);
    }

    public static double insuranceEV(Shoe shoe) {
        double ev = shoe.count(10) * 1.0 / shoe.getCards().size() - (shoe.getCards().size() - shoe.count(10)) * 1.0 / shoe.getCards().size();
        if(ev > 0) { return ev; };
        return 0;
    }


}