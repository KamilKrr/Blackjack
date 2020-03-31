package Simulation_Environment;

public class Blackjack {
    private InputProcessor inputProcessor;

    private Shoe shoe;

    private Dealer dealer;

    private Player[] players;

    private BasicStrategyPlayer basicStrategyPlayer;

    private boolean print = false;

    private double BANKROLL = 1000;

    public Blackjack(int humanCount, int botCount){
        newGame(humanCount, botCount);
        inputProcessor = new InputProcessor();
        basicStrategyPlayer = new BasicStrategyPlayer();
    }

    public void newGame(int humanCount, int botCount){
        dealer = new Dealer();

        shoe = new Shoe(8, 50);

        players = new Player[humanCount + botCount];

        for (int i = 0; i < humanCount + botCount; i++) {
            players[i] = new Player(i >= humanCount ? true : false, BANKROLL);
        }
    }

    public void newRound(){
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
                double bet;
                if(players[i].isAuto()){
                    bet = basicStrategyPlayer.placeBet(players[i].getStartingBankroll());
                    players[i].placeBet(bet);
                    break;
                }
                bet = inputProcessor.placeBet();
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
        dealer.acceptCard(shoe.dealOne(true));
        dealer.acceptCard(shoe.dealOne(false));

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
                        if(print) {
                            String possibleActions = "stand(s)|hit(h)";
                            if(currentHand.getNumberOfCards() == 2 && currentPlayer.getBet() * 2 <= currentPlayer.getBankroll()) possibleActions += "|double(d)";
                            if(currentHand.isDoubleCard() && currentPlayer.getBet() * 2 <= currentPlayer.getBankroll()) possibleActions += "|split(p)";
                            if(dealer.isAce()) possibleActions += "|insurance(i)";
                            System.out.println(possibleActions);
                        }
                        String action;

                        if(currentPlayer.isAuto()){
                            action = basicStrategyPlayer.nextAction(dealer.getSum(), currentHand.getSum(), currentHand.containsAce(), currentHand.isDoubleCard(), (int)currentPlayer.getBet(), (int)currentPlayer.getBankroll()-(int)currentPlayer.getBet(), currentHand.getNumberOfCards());
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
                            }else if(currentPlayer.getBet() * 2 > currentPlayer.getBankroll()){
                                if(print)
                                    System.out.println("You cannot split if you dont have twice the money in your bankroll");
                                continue;
                            }
                            Card doubleCard = currentHand.getLastCard();

                            currentHand.removeLast();

                            Hand secondHand = new Hand();
                            secondHand.acceptCard(doubleCard);

                            currentHand.acceptCard(shoe.dealOne(true));
                            secondHand.acceptCard(shoe.dealOne(true));

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
                            }else if(currentPlayer.getBet() * 2 > currentPlayer.getBankroll()){
                                if(print)
                                    System.out.println("You cannot double if you dont have twice the money in your bankroll");
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
