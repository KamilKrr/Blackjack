package Simulation_Environment;

public class Blackjack {
    private InputProcessor inputProcessor;

    private Deck deck;

    private Dealer dealer;

    private Player[] players;

    public Blackjack(int playerCount){
        newGame(playerCount);
    }

    public void newGame(int playerCount){
        dealer = new Dealer();

        deck = new Deck();

        players = new Player[playerCount];

        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player();
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
                players[i].myHands.get(j).acceptCard(deck.dealOne(true));
                players[i].myHands.get(j).acceptCard(deck.dealOne(true));
            }
        }

        //let each player choose an action for each hand
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].myHands.size(); j++) {
                players[i].myHands.get(j).
            }
        }
        String action = inputProcessor.chooseAction();



    }

}
