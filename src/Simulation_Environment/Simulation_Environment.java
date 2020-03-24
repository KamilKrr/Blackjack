package Simulation_Environment;

import java.util.Scanner;

public class Simulation_Environment {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Deck deck = new Deck();

        while (deck.cardsLeft() > 20) {
            Hand dealer = new Hand();
            dealer.acceptCard(deck.dealOne(true));
            dealer.acceptCard(deck.dealOne(false));

            Hand player = new Hand();
            Card ace = new Card("ACE", 1);
            Card ten = new Card("TEN", 10);
            ace.setFaceUp(true);
            ten.setFaceUp(true);
            //player.acceptCard(deck.dealOne(true));
            //player.acceptCard(deck.dealOne(true));
            player.acceptCard(ten);
            player.acceptCard(ten);


            System.out.println("Dealer: " + dealer.displayHand() + " |Sum: " + dealer.getSum());
            System.out.println("Player: " + player.displayHand() + " |Sum: " + player.getSum());

            while (true) {
                if (player.getSum() == 21) {
                    break;
                } else if (player.isDoubleCard()) {
                    System.out.println("_____");
                    System.out.println("Choose your action: (s for stand, h for hit, d for double, p for split)");
                    String action = sc.nextLine();
                    if (action.equals("h")) {
                        player.acceptCard(deck.dealOne(true));
                        System.out.println("Player: " + player.displayHand() + " |Sum: " + player.getSum());

                        if (player.getSum() > 21) {
                            break;
                        }
                    } else if (action.equals("s")) {
                        break;
                    } else if (action.equals("d")) {
                        player.acceptCard(deck.dealOne(true));
                        System.out.println("Player: " + player.displayHand() + " |Sum: " + player.getSum());
                        break;
                    } else if (action.equals("p")) {
                        player.removeLast();
                    }
                } else {
                    System.out.println("_____");
                    System.out.println("Choose your action: (s for stand, h for hit, d for double)");
                    String action = sc.nextLine();
                    if (action.equals("h")) {
                        player.acceptCard(deck.dealOne(true));
                        System.out.println("Player: " + player.displayHand() + " |Sum: " + player.getSum());

                        if (player.getSum() > 21) {
                            break;
                        }
                    } else if (action.equals("s")) {
                        break;
                    } else if (action.equals("d")) {
                        player.acceptCard(deck.dealOne(true));
                        System.out.println("Player: " + player.displayHand() + " |Sum: " + player.getSum());
                        break;
                    }
                }
            }

            dealer.reveal();

            while (dealer.getSum() < 17) {
                dealer.acceptCard(deck.dealOne(true));
            }

            System.out.println("Dealer: " + dealer.displayHand() + " |Sum: " + dealer.getSum());

            int dealerSum = dealer.getSum();
            int playerSum = player.getSum();

            System.out.println("You: " + playerSum + " Dealer: " + dealerSum);

            if (playerSum > 21 || (playerSum < dealerSum && dealerSum <= 21)) {
                System.out.println("You loose");
            } else {
                System.out.println("You win");
            }

            System.out.println("----------------------------------");
        }

    }


}
