package Strategy_Engine;


import java.util.*;

public class Engine {
    public static HashMap<Integer, Double> dealer = new HashMap<>();
    public static int otherCount = 1;
    public static int initCount = 1;
    public static int recursionCount = 1;
    public static int endCount = 1;

    public static String bestMove(Shoe shoe, int dealerCard, int playerSum, boolean isPlayerSoftHand, List<String> moves, HashMap<Integer, Double> probabilities) {
        otherCount++;
        if (moves.contains("insurance")) {
            if(insuranceEV(shoe) > 0) { return "insurance"; }
            return "no insurance";
        }

        for (int i = 17; i <= 22; i++) {
            dealer.put(i, 0.0);
        }
        boolean dealerSoftHand = (dealerCard == 11);
        //HashMap<Integer, Double> probabilities = dealerProbabilities(shoe, dealerCard, 1.0, dealer, dealerSoftHand);

        double h = playerHitEV(shoe, probabilities, playerSum, isPlayerSoftHand, 0);
        double s = playerStandEV(probabilities, playerSum);
        int count = 2;
        double d = 0;
        double p = 0;

        //System.out.println(probabilities);
        /* System.out.println("Dealer shows " + dealerCard);
        System.out.println("Your hand: " + playerSum);
        System.out.println("-----------------");
        System.out.println("Expected value when hitting: " + (h*100+100) + "% of your bet");
        System.out.println("Expected value when standing: " + (s*100+100) + "% of your bet"); */
        if (moves.contains("double")) { d = playerDoubleEV(shoe, probabilities, playerSum, isPlayerSoftHand); count++; /*System.out.println("Expected value when doubling: " + (d*100+100) + "% of your bet"); */}
        if (moves.contains("split")) { p = playerSplitEV(shoe, probabilities, playerSum, isPlayerSoftHand); count++; /*System.out.println("Expected value when splitting: " + (p*100+100) + "% of your bet"); */}
        /* System.out.println("-----------------"); */

        String nextMove = "";
        if ((count == 2 && h >= s) || (count == 3 && h >= s && h >= d) || (count == 4 && h >= s && h >= d && h >= p )) { nextMove = "hit"; }
        else if ((count == 2 && s >= h) || (count == 3 && s >= h && s >= d) || (count == 4 && s >= h && s >= d && s >= p )) { nextMove = "stand"; }
        else if ((count == 3 && d >= h && d >= s) || (count == 4 && d >= h && d >= s && d >= p )) { nextMove = "double"; }
        else if (count == 4 && p >= h && p >= s && p >= d) { nextMove = "split"; }
        else{ System.out.println("bestMove ERROR"); }


        return nextMove;
    }

    public static double advantageCalculation(Shoe shoe) {
        double ev = 0;
        List<String> moves3 = getMoves(3);
        List<String> moves4 = getMoves(4);

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                for (int k = 1; k <= 10; k++) {
                    if (i != 1 && j != 1 && i != j) {
                        if (k != 1) {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), k, (i + j), false, moves3) * cardProbability(shoe, new int[]{i, j, k});
                        } else {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), 11, (i + j), false, moves3) * cardProbability(shoe, new int[]{i, j, k});
                            ev += insuranceEV(makeShoe(shoe, new int[]{i, j, k}));
                        }
                    } else if (i == j && i != 1) {
                        if (k != 1) {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), k, (i + j), false, moves4) * cardProbability(shoe, new int[]{i, j, k});
                        } else {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), 11, (i + j), false, moves4) * cardProbability(shoe, new int[]{i, j, k});
                            ev += insuranceEV(makeShoe(shoe, new int[]{i, j, k}));
                        }
                    } else if (i == j) {
                        if (k != 1) {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), k, 12, true, moves4) * cardProbability(shoe, new int[]{i, j, k});
                        } else {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), 11, 12, true, moves4) * cardProbability(shoe, new int[]{i, j, k});
                            ev += insuranceEV(makeShoe(shoe, new int[]{i, j, k}));
                        }
                    } else if ((i + j) == 11){
                        if (k != 1) {
                            ev += 1.5 * cardProbability(shoe, new int[]{i, j, k});
                        } else {
                            ev += 1.5 * cardProbability(shoe, new int[]{i, j, k});;
                            ev += insuranceEV(makeShoe(shoe, new int[]{i, j, k}));
                        }
                    } else {
                        if (k != 1) {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), k, (i + j + 10), true, moves3) * cardProbability(shoe, new int[]{i, j, k});
                        } else {
                            ev += bestMoveEV(makeShoe(shoe, new int[]{i, j, k}), 11, (i + j + 10), true, moves3) * cardProbability(shoe, new int[]{i, j, k});
                            ev += insuranceEV(makeShoe(shoe, new int[]{i, j, k}));
                        }
                    }
                }
            }
        }

        return ev;
    }

    public static double bestMoveEV (Shoe shoe, int dealerCard, int playerSum, boolean isPlayerSoftHand, List<String> moves) {
        for (int i = 17; i <= 22; i++) { dealer.put(i, 0.0); }
        endCount++;
        HashMap<Integer, Double> probabilities = dealerProbabilities(shoe, dealerCard, 1.0, dealer, isPlayerSoftHand);
        String move = bestMove(shoe, dealerCard, playerSum, isPlayerSoftHand, moves, probabilities);
        if(move.equals("hit")) { return playerHitEV(shoe, probabilities, playerSum, isPlayerSoftHand, 0); }
        if(move.equals("stand")) { return playerStandEV(probabilities, playerSum); }
        if(move.equals("double")) { return playerDoubleEV(shoe, probabilities, playerSum, isPlayerSoftHand); }
        if(move.equals("split")) { return playerSplitEV(shoe, probabilities, playerSum, isPlayerSoftHand); }
        else { System.out.println("convertMoveToEV ERROR"); return -1000.0; }
    }

    public static double cardProbability (Shoe shoe, int[] cards) {
        otherCount++;
        double probability = 1.0;
        Shoe s = new Shoe(shoe);
        for (int i = 0; i < cards.length; i++) {
            probability *= s.count(cards[i]) * 1.0 / s.getCards().size();
            s.remove(i);
        }
        return probability;
    }

    public static List<String> getMoves (int numberOfMoves) {
        otherCount++;
        List<String> moves = new ArrayList<>();
        if(numberOfMoves > 4 || numberOfMoves < 2) { System.out.println("getMoves ERROR"); return moves; }
        moves.add("hit");
        moves.add("stand");
        if(numberOfMoves == 2) { return moves; }
        moves.add("double");
        if(numberOfMoves == 3) { return moves; }
        moves.add("split");
        return moves;
    }

    public static Shoe makeShoe (Shoe shoe, int[] cards) {
        otherCount++;
        Shoe s = new Shoe(shoe);
        for (int i = 0; i < cards.length; i++) {
            s.remove(cards[i]);
        }
        return s;
    }

    public static HashMap<Integer, Double> dealerProbabilities(Shoe shoe, int dealerCard, double probability, HashMap<Integer, Double> probabilities, boolean softHand) {
        //exit condition
        initCount++;
        if (probability == 0) return probabilities;
        recursionCount++;

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

    public static double playerHitEV(Shoe shoe, HashMap<Integer, Double> probabilities, int playerSum, boolean softHand, double ev) {
        for (int i = playerSum + 1; i <= playerSum + 10; i++) {
            if (i - playerSum != 1 || i >= 11) {
                if (i >= 22 && !softHand) {
                    ev -= shoe.count(i - playerSum) * 1.0 / shoe.getCards().size();
                } else if (i >= 22) {
                    Shoe s = new Shoe(shoe);
                    s.remove(i - playerSum);
                    ev += Math.max(shoe.count(i - playerSum) * 1.0 / shoe.getCards().size() * playerHitEV(s, probabilities, i - 10, false, ev), shoe.count(i - playerSum) * 1.0 / shoe.getCards().size() * playerStandEV(probabilities, i - 10));
                } else {
                    Shoe s = new Shoe(shoe);
                    s.remove(i - playerSum);
                    ev += Math.max(shoe.count(i - playerSum) * 1.0 / shoe.getCards().size() * playerHitEV(s, probabilities, i, softHand, ev), shoe.count(i - playerSum) * 1.0 / shoe.getCards().size() * playerStandEV(probabilities, i));
                }
            } else {
                Shoe s = new Shoe(shoe);
                s.remove(i - playerSum);
                ev += Math.max(shoe.count(1) * 1.0 / shoe.getCards().size() * playerHitEV(s, probabilities, i + 10, true, ev), shoe.count(1) * 1.0 / shoe.getCards().size() * playerStandEV(probabilities, i + 10));
            }
        }

        return ev;
    }

    public static double playerSplitEV(Shoe shoe, HashMap<Integer, Double> probabilities, int playerSum, boolean softHand) {
        if(softHand) { return playerDoubleEV(shoe, probabilities, 11, true); }
        return 2.0 * playerHitEV(shoe, probabilities, playerSum/2, false, 0);
    }

    public static double insuranceEV(Shoe shoe) {
        double ev = shoe.count(10) * 1.0 / shoe.getCards().size() - (shoe.getCards().size() - shoe.count(10)) * 1.0 / shoe.getCards().size();
        if(ev > 0) { return ev; };
        return 0;
    }

}