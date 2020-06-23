package Simulation_Environment;

import Strategy_Engine.Observer;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {
    private InputProcessor inputProcessor;

    private Shoe shoe;

    private Dealer dealer;

    private Player[] players;

    private boolean print = false;
    private boolean debugEngine = false;

    private double BANKROLL = 1000000;

    //Card counter for the Strategy Engine
    private Observer observer = new Observer(8);

    public Blackjack(int humanPlayerCount, int basicStrategyPlayerCount, int strategyEnginePlayerCount){
        if(humanPlayerCount > 0) {
            print = true;
        }
        newGame(humanPlayerCount, basicStrategyPlayerCount, strategyEnginePlayerCount);
        inputProcessor = new InputProcessor();
    }

    public void newGame(int humanPlayerCount, int basicStrategyPlayerCount, int strategyEnginePlayerCount){
        dealer = new Dealer();

        shoe = new Shoe(8, 50);

        players = new Player[humanPlayerCount + basicStrategyPlayerCount + strategyEnginePlayerCount];

        for (int i = 0; i < humanPlayerCount; i++) {
            players[i] = new HumanPlayer(BANKROLL);
        }
        for (int i = 0; i < basicStrategyPlayerCount; i++) {
            players[humanPlayerCount + i] = new BasicStrategyPlayer(BANKROLL);
        }
        for (int i = 0; i < strategyEnginePlayerCount; i++) {
            players[humanPlayerCount + basicStrategyPlayerCount + i] = new StrategyEnginePlayer(BANKROLL);
        }
    }

    public void newRound(){

        if(shoe.usedOver(70)){
            shoe.assembleNewShoe();
            observer.restoreAllCards();
            //System.out.println("reassembling");
        }
        //hand of the dealer
        dealer.newHand();

        //hand of each player
        for (int i = 0; i < players.length; i++) {
            if(players[i].isBancrupt()) continue;

            players[i].removeHands();
            players[i].addHand(new Hand());

            if(print)
                System.out.println("Player " + players[i].getName() + " your bankroll is " + players[i].getBankroll() + "€, please place your bet as a multiple of 5:");
            while(true){
                double bet = players[i].placeBet();
                if(players[i].canPlaceBet(bet)){
                    players[i].placeBet(bet);
                    break;
                }
                if(print)
                    System.out.println("Cannot place this bet, please choose again");
            }
        }

        //2 cards for the dealer
        //one hidden one visible
        Card upcard = shoe.dealOne(true);
        Card hiddenCard = shoe.dealOne(false);
        dealer.acceptCard(upcard);
        dealer.acceptCard(hiddenCard);

        //card coutning
        //only the upcard is visible at this point
        observer.observeCard(upcard);


        //2 cards for each hand for each player
        //both visible
        for (int i = 0; i < players.length; i++) {
            if(players[i].isBancrupt()) continue;

            for (int j = 0; j < players[i].myHands.size(); j++) {

                /*
                Card ace = new Card("ACE", 1);
                Card ten = new Card("ACE", 1);

                ace.setFaceUp(true);
                ten.setFaceUp(true);

                players[i].myHands.get(j).acceptCard(ace);
                players[i].myHands.get(j).acceptCard(ace);
                /**/

                Card card1 = shoe.dealOne(true);
                Card card2 = shoe.dealOne(true);

                players[i].myHands.get(j).acceptCard(card1);
                players[i].myHands.get(j).acceptCard(card2);

                //card counting
                observer.observeCard(card1);
                observer.observeCard(card2);

            }
        }

        //TODO: Interface
        //show dealer cards
        if(print)
            System.out.println(dealer.getHand().displayHand());

        //let each player choose an action for each hand
        for (int i = 0; i < players.length; i++) {
            if(players[i].isBancrupt()) continue;

            for (int j = 0; j < players[i].myHands.size(); j++) {
                Player currentPlayer = players[i];
                Hand currentHand = currentPlayer.myHands.get(j);
                while (true){

                    //TODO: Interface
                    if(print){
                        System.out.println(currentPlayer.getName() + "'s turn, Hand #" + (j+1));
                        System.out.println(currentHand.displayHand());
                        System.out.println("Sum: " + currentHand.getSum());
                    }

                    if(currentHand.getSum() == 21 && currentHand.getNumberOfCards() == 2 && !currentHand.isSplitted()){
                        if(print)
                            System.out.println("Blackjack!");
                        currentHand.blackjack();
                        break;
                    }else if(currentHand.getSum() == 21) {
                        break;
                    }else if(currentHand.getSum() > 21){
                        break;
                    }else{
                        //check if is blocked (after splitting two aces)
                        if(currentHand.isBlocked()){
                            break;
                        }

                        List<String> moves = new ArrayList<>();
                        String possibleActions = "stand(s)|hit(h)";
                        moves.add("hit");
                        moves.add("stand");
                        if(currentHand.getNumberOfCards() == 2 && currentPlayer.getBet() * 2 <= currentPlayer.getBankroll()) {
                            possibleActions += "|double(d)";
                            moves.add("double");
                        }
                        if(currentHand.isDoubleCard() && currentPlayer.getBet() * 2 <= currentPlayer.getBankroll()){
                            possibleActions += "|split(p)";
                            moves.add("split");
                        }
                        if(dealer.isAce()) possibleActions += "|insurance(i)";
                        if(print)
                            System.out.println(possibleActions);
                        String action = inputProcessor.normalize(currentPlayer.nextAction(observer, moves, dealer.getSum(), currentHand.getSum(), currentHand.containsAce(), currentHand.isDoubleCard(), (int)currentPlayer.getBet(), (int)currentPlayer.getBankroll()-(int)currentPlayer.getBet(), currentHand.getNumberOfCards()));


                        //debug engine vs basic strategy
                        if(debugEngine){
                            String basicSTrategyAction = inputProcessor.normalize(new BasicStrategyPlayer(BANKROLL).nextAction(observer, moves, dealer.getSum(), currentHand.getSum(), currentHand.containsAce(), currentHand.isDoubleCard(), (int)currentPlayer.getBet(), (int)currentPlayer.getBankroll()-(int)currentPlayer.getBet(), currentHand.getNumberOfCards()));
                            String engineAction = inputProcessor.normalize(new StrategyEnginePlayer(BANKROLL).nextAction(observer, moves, dealer.getSum(), currentHand.getSum(), currentHand.containsAce(), currentHand.isDoubleCard(), (int)currentPlayer.getBet(), (int)currentPlayer.getBankroll()-(int)currentPlayer.getBet(), currentHand.getNumberOfCards()));

                            if(basicSTrategyAction != engineAction){
                                System.out.println("-------------------");
                                System.out.println("Basic strategy says: " + basicSTrategyAction);
                                System.out.println("Engine says: " + engineAction);
                                System.out.println("Dealer: " + dealer.getSum());
                                System.out.println("Player: " + currentHand.displayHand());
                                System.out.println("Cards in deck: " + observer.getShoe().getCards());
                                System.out.println("-------------------");
                            }
                        }
                        if(print)
                            System.out.println(currentPlayer.getName() + " chooses " + action);

                        //----------SPLIT----------
                        if(action.equals("split")){
                            if(!currentHand.isDoubleCard()){
                                if(print)
                                    System.out.println("You can only split when you have 2 equal cards!");
                                continue;
                            }else if(currentPlayer.getBet() * 2 > currentPlayer.getBankroll()){
                                if(print)
                                    System.out.println("You cannot split if you dont have twice the money in your bankroll");
                                continue;
                            }
                            Card doubleCard = currentHand.getLastCard();

                            currentHand.removeLast();

                            Hand secondHand = new Hand();
                            secondHand.acceptCard(doubleCard);


                            Card card1 = shoe.dealOne(true);
                            Card card2 = shoe.dealOne(true);

                            currentHand.acceptCard(card1);
                            secondHand.acceptCard(card2);

                            //card counting
                            observer.observeCard(card1);
                            observer.observeCard(card2);

                            currentHand.split();
                            secondHand.split();

                            if(doubleCard.isAce()){
                                currentHand.block();
                                secondHand.block();
                            }

                            currentPlayer.addHand(secondHand);
                            continue;
                        }

                        //----------HIT----------
                        if(action.equals("hit")){

                            Card card = shoe.dealOne(true);

                            currentHand.acceptCard(card);

                            //card counting
                            observer.observeCard(card);

                            continue;
                        }

                        //----------STAND----------
                        if(action.equals("stand")){
                            break;
                        }

                        //----------DOUBLE----------
                        if(action.equals("double")){
                            if(currentHand.getNumberOfCards() != 2){
                                if(print)
                                    System.out.println("You can only double when you have 2 cards!");
                                continue;
                            }else if(currentPlayer.getBet() * 2 > currentPlayer.getBankroll()){
                                if(print)
                                    System.out.println("You cannot double if you dont have twice the money in your bankroll");
                                continue;
                            }

                            Card card = shoe.dealOne(true);

                            currentHand.acceptCard(card);

                            //card counting
                            observer.observeCard(card);


                            currentHand.doubleDown();
                            break;
                        }

                        if(print)
                            System.out.println("Invalid action!");
                    }
                }
            }
        }

        dealer.reveal();

        //card counting
        //now that the dealer revealed both cards also count the second one
        observer.observeCard(hiddenCard);

        while(dealer.getSum() < dealer.passesOn){
            Card card = shoe.dealOne(true);

            dealer.acceptCard(card);

            //card counting
            observer.observeCard(card);
        }

        if(print)
            System.out.println("Dealer: " + dealer.displayHand() + " |Sum: " + dealer.getSum());

        int dealerSum = dealer.getSum();

        //compare each Hand to dealer
        //pay prizes
        for (int i = 0; i < players.length; i++) {
            if(players[i].isBancrupt()) continue;

            for (int j = 0; j < players[i].myHands.size(); j++) {
                Player currentPlayer = players[i];
                Hand currentHand = currentPlayer.myHands.get(j);

                int playerSum = currentHand.getSum();

                if(print)
                    System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + ": " + playerSum + " vs Dealer: " + dealerSum);

                if (playerSum > 21 || (playerSum < dealerSum && dealerSum <= 21)) {
                    double difference = currentPlayer.getBet();
                    if(currentHand.hasDoubled()) {
                        difference *= 2;
                    }
                    currentPlayer.lose(difference);
                    dealer.cash += difference;
                    if(print) {
                        System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j + 1) + " loses!");
                        System.out.println("Player " + currentPlayer.getName() + " Money: " + currentPlayer.getBankroll());
                    }
                } else if(playerSum == dealerSum){
                    currentPlayer.draw();
                    if(print){
                        System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " draws!");
                        System.out.println("Player " + currentPlayer.getName() + " Money: " + currentPlayer.getBankroll());
                    }
                }else{
                    double difference = currentPlayer.getBet();
                    if(currentHand.isBlackjack()) {
                        difference *= 1.5;
                    }else if(currentHand.hasDoubled()) {
                        difference *= 2;
                    }
                    currentPlayer.win(difference);
                    dealer.cash -= difference;
                    if(print){
                        System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " wins!");
                        System.out.println("Player " + currentPlayer.getName() + " Money: " + currentPlayer.getBankroll());
                    }
                }
            }
        }
        /*
        shoe.display();
        observer.display();
        /**/
    }

    public void printStats(){
        System.out.println("-------------------------");
        System.out.println("STATS");
        System.out.println("-------------------------");
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];

            System.out.println(player.getName() + " -> " + player.stats());
        }
        System.out.println("-------------------------");
        System.out.println("Dealer cash: " + dealer.cash);
        System.out.println("-------------------------");

    }
}
