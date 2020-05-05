package Strategy_Engine;

public class Strategy_Engine {
    public static void main(String[] args) {
        Observer o = new Observer(3);

        System.out.println("-----------------------");

        Observer o2 = new Observer(1);

        int dealerCard = 9;


        Engine.bestMove(o2.getShoe(), dealerCard, 5, false);
    }

    public String bestMove(){
        //return Engine.bestMove();
        return "";
    }
}
