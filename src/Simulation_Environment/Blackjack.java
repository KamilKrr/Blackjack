package Simulation_Environment;

public class Blackjack {
    private InputProcessor inputProcessor;

    private Shoe shoe;

    private Dealer dealer;

    private Player[] players;

    private BasicStrategyPlayer basicStrategyPlayer;

    private boolean print = false;

    public Blackjack(int humanCount, int botCount){
        newGame(humanCount, botCount);
        inputProcessor = new InputProcessor();
        basicStrategyPlayer = new BasicStrategyPlayer();
    }

    public void newGame(int humanCount, int botCount){
        dealer = new Dealer();

        shoe = new Shoe(8, 70);

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
            players[i].removeHands();
            players[i].addHand(new Hand());
        }

        //2 cards for the dealer
        //one hidden one visible
        dealer.acceptCard(shoe.dealOne(true));
        dealer.acceptCard(shoe.dealOne(false));

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
                players[i].myHands.get(j).acceptCard(shoe.dealOne(true));
                players[i].myHands.get(j).acceptCard(shoe.dealOne(true));
            }
        }

        //TODO: Interface
        //show dealer cards
        if(print)
            System.out.println(dealer.getHand().displayHand());

        //let each player choose an action for each hand
        for (int i = 0; i < players.length; i++) {
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

                    if(currentHand.getSum() == 21){
                        if(print)
                            System.out.println("Blackjack!");
                        break;
                    }else if(currentHand.getSum() > 21){
                        break;
                    }else{
                        //check if is blocked (after splitting two aces)
                        if(currentHand.isBlocked()){
                            break;
                        }
                        if(print) {
                            String possibleActions = "stand(s)|hit(h)";
                            if(currentHand.getNumberOfCards() == 2) possibleActions += "|double(d)";
                            if(currentHand.isDoubleCard()) possibleActions += "|split(p)";
                            if(dealer.isAce()) possibleActions += "|insurance(i)";
                            System.out.println(possibleActions);
                        }
                        String action;

                        if(currentPlayer.isAuto()){
                            action = basicStrategyPlayer.nextAction(dealer.getSum(), currentHand.getSum(), currentHand.containsAce(), currentHand.isDoubleCard(), 5, 100, currentHand.getNumberOfCards());
                            action = inputProcessor.normalize(action);
                            if(print)
                                System.out.println("Basic Srategy Player chooses " + action);

                        }else{
                            action = inputProcessor.chooseAction();
                            if(print)
                                System.out.println("You choose " + action);
                        }


                        //----------SPLIT----------
                        if(action.equals("split")){
                            if(!currentHand.isDoubleCard()){
                                if(print)
                                    System.out.println("You can only split when you have 2 equal cards!");
                                continue;
                            }
                            Card doubleCard = currentHand.getLastCard();

                            currentHand.removeLast();

                            Hand secondHand = new Hand();
                            secondHand.acceptCard(doubleCard);

                            currentHand.acceptCard(shoe.dealOne(true));
                            secondHand.acceptCard(shoe.dealOne(true));

                            if(doubleCard.isAce()){
                                currentHand.block();
                                secondHand.block();
                            }

                            currentPlayer.addHand(secondHand);
                            continue;
                        }

                        //----------HIT----------
                        if(action.equals("hit")){
                            currentHand.acceptCard(shoe.dealOne(true));
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
                            }

                            currentHand.acceptCard(shoe.dealOne(true));
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

        while(dealer.getSum() < dealer.passesOn){
            dealer.acceptCard(shoe.dealOne(true));
        }

        if(print)
            System.out.println("Dealer: " + dealer.displayHand() + " |Sum: " + dealer.getSum());

        int dealerSum = dealer.getSum();

        //compare each Hand to dealer
        //pay prizes
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].myHands.size(); j++) {
                Player currentPlayer = players[i];
                Hand currentHand = currentPlayer.myHands.get(j);

                int playerSum = currentHand.getSum();

                if(print)
                    System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + ": " + playerSum + " vs Dealer: " + dealerSum);

                if (playerSum > 21 || (playerSum < dealerSum && dealerSum <= 21)) {
                    currentPlayer.lose();
                    if(print)
                        System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " loses!");
                } else if(playerSum == dealerSum){
                    currentPlayer.draw();
                    if(print)
                        System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " draws!");
                }else{
                    currentPlayer.win();
                    if(print)
                        System.out.println("Player " + currentPlayer.getName() + ", Hand #" + (j+1) + " wins!");
                }
            }
        }
    }

    public void printStats(){
        System.out.println("-------------------------");
        System.out.println("STATS");
        System.out.println("-------------------------");
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];

            System.out.println(player.getName() + " -> " + player.stats());
        }
    }
}
