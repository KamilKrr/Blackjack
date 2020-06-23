package Simulation_Environment;

import java.util.ArrayList;

public class RoundSimulator {

    public static void main(String[] args) {
        Shoe shoe = new Shoe(8, 50);

        RoundSimulator rs = new RoundSimulator(shoe);

        System.out.println(rs.simulate(6, 12, "hit", 1000000));
        System.out.println(rs.simulate(6, 12, "stand", 1000000));

    }

    Shoe shoe;

    public RoundSimulator(Shoe shoe){
        this.shoe = shoe;


    }

    public int simulate(int dealerSum, int playerSum, String move, int iterations){
        int points = 0;

        Card[] cards = shoe.getCards();

        for (int i = 0; i < iterations; i++) {
            ArrayList<Integer> cardsUsed = new ArrayList<Integer>();
            int temp_dealerSum = dealerSum;
            int temp_playerSum = playerSum;

            //player
            if(move.equals("hit") || move.equals("double")){
                temp_playerSum += getRandomUnusedCard(cards, cardsUsed);
                if(temp_playerSum > 21){
                    if(move.equals("double")){
                        points -= 2;
                    }else{
                        points -= 1;
                    }
                    continue;
                }
                points += Math.max(simulate(dealerSum, temp_playerSum, "hit", 1), simulate(dealerSum, temp_playerSum, "stand", 1));
            }

            //dealer
            while(temp_dealerSum < 17){
                temp_dealerSum += getRandomUnusedCard(cards, cardsUsed);
            }

            //result = how many points for this round
            //= for draw
            int result = 0;

            if(temp_playerSum > 21){
                result = -1;
            }else if(temp_dealerSum > 21){
                result = 1;
            }else if(temp_dealerSum > temp_playerSum){
                result = -1;
            }else if(temp_playerSum > temp_dealerSum){
                result = 1;
            }

            if(move.equals("double")){
                result *= 2;
            }
            points += result;
        }

        return points;
    }

    private int getRandomUnusedCard(Card[] cards, ArrayList<Integer> cardsUsed){
        int randomCardIndex;
        while(true){
            randomCardIndex = (int)(Math.random()*cards.length);
            if(!cardsUsed.contains(randomCardIndex)) break;
        }
        cardsUsed.add(randomCardIndex);
        return cards[randomCardIndex].getMyValue();
    }
}
