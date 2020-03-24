package Simulation_Environment;

public class Blackjack {
    private InputProcessor inputProcessor;

    private Deck deck;

    private Dealer dealer;

    private Player[] players;

    private BasicStrategyPlayer basicStrategyPlayer;

    public static void main(String[] args) {
        Blackjack bj = new Blackjack(0, 10);

        bj.newRound();
    }

    public Blackjack(int humanCount, int botCount){
        newGame(humanCount, botCount);
        inputProcessor = new InputProcessor();
        basicStrategyPlayer = new BasicStrategyPlayer();
    }

    public void newGame(int humanCount, int botCount){
        dealer = new Dealer();

        deck = new Deck();

        players = new Player[humanCount + botCount];

        for (int i = 0; i < humanCount + botCount; i++) {
            players[i] = new Player(i >= humanCount ? true : false);
        }
    }

    public void newRound(){
        //hand of the dealer
        dealer.newHand();

        //hand of each player
        for (int i = 0; i < players.length; i++) {
            players[i].addHand(new Hand());
        }

        //2 cards for the dealer
        //one hidden one visible
        dealer.acceptCard(deck.dealOne(true));
        dealer.acceptCard(deck.dealOne(false));

        //2 cards for each hand for each player
        //both visible
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].myHands.size(); j++) {

                /*
                Card ace = new Card("ACE", 1);
                Card ten = new Card("TEN", 10);

                ace.setFaceUp(true);
                ten.setFaceUp(true);

                players[i].myHands.get(j).acceptCard(ace);
                players[i].myHands.get(j).acceptCard(ace);
                /**/
                players[i].myHands.get(j).acceptCard(deck.dealOne(true));
                players[i].myHands.get(j).acceptCard(deck.dealOne(true));
            }
        }

        //TODO: Interface
        //show dealer cards
        System.out.println(dealer.getHand().displayHand());

        //let each player choose an action for each hand
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].myHands.size(); j++) {
                Player currentPlayer = players[i];
                Hand currentHand = currentPlayer.myHands.get(j);
                while (true){

                    //TODO: Interface
                    System.out.println(currentPlayer.getName() + "'s turn, Hand #" + (j+1));
                    System.out.println(currentHand.displayHand());
                    System.out.println("Sum: " + currentHand.getSum());

                    if(currentHand.getSum() == 21){
                        System.out.println("Blackjack!");
                        break;
                    }else if(currentHand.getSum() > 21){
                        break;
                    }else{
                        //check if is blocked (after splitting two aces)
                        if(currentHand.isBlocked()){
                            break;
                        }
                        System.out.println("Choose your action: stand(s)|hit(h)|double(d)|split(p)|insurance(i)");
                        String action;

                        if(currentPlayer.isAuto()){
                            action = basicStrategyPlayer.nextAction(dealer.getSum(), currentHand.getSum(), currentHand.containsAce(), currentHand.isDoubleCard(), 5, 100, currentHand.getNumberOfCards());
                            action = inputProcessor.normalize(action);
                            System.out.println("Basic Srategy Player chooses " + action);

                        }else{
                            action = inputProcessor.chooseAction();
                            System.out.println("You choose " + action);
                        }


                        //----------SPLIT----------
                        if(action.equals("split")){
                            if(!currentHand.isDoubleCard()){
                                System.out.println("You can only split when you have 2 equal cards!");
                                continue;
                            }
                            Card doubleCard = currentHand.getLastCard();

                            currentHand.removeLast();

                            Hand secondHand = new Hand();
                            secondHand.acceptCard(doubleCard);

                            if(doubleCard.isAce()){
                                currentHand.acceptCard(deck.dealOne(true));
                                currentHand.block();
                                secondHand.acceptCard(deck.dealOne(true));
                                secondHand.block();
                            }

                            currentPlayer.addHand(secondHand);
                            continue;
                        }

                        //----------HIT----------
                        if(action.equals("hit")){
                            currentHand.acceptCard(deck.dealOne(true));
                            continue;
                        }

                        //----------STAND----------
                        if(action.equals("stand")){
                            break;
                        }

                        //----------DOUBLE----------
                        if(action.equals("double")){
                            if(currentHand.getNumberOfCards() != 2){
                                System.out.println("You can only double when you have 2 cards!");
                            }

                            currentHand.acceptCard(deck.dealOne(true));
                            currentHand.doubleDown();
                            break;
                        }
                    }
                }
            }
        }

        dealer.reveal();

        while(dealer.getSum() < dealer.passesOn){
            dealer.acceptCard(deck.dealOne(true));
        }

        System.out.println("Dealer: " + dealer.displayHand() + " |Sum: " + dealer.getSum());

        int dealerSum = dealer.getSum();

        //compare each Hand to dealer
        //pay prizes
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].myHands.size(); j++) {
                Player currentPlayer = players[i];
                Hand currentHand = currentPlayer.myHands.get(j);

                int playerSum = currentHand.getSum();

                System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + ": " + playerSum + " vs Dealer: " + dealerSum);

                if (playerSum > 21 || (playerSum < dealerSum && dealerSum <= 21)) {
                    System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " loses!");
                } else if(playerSum == dealerSum){
                    System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " draws!");
                }else{
                    System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " wins!");
                }
            }
        }

    }

}
