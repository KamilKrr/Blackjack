package Strategy_Engine;

public class Strategy_Engine {
    public static void main(String[] args) {
        Observer o = new Observer(3);

        System.out.println("-----------------------");

        Observer o2 = new Observer(1);

        int dealerCard = 9;

        o2.removeCard(9);


        Engine.bestMove(o2.getShoe(), dealerCard, 5, false);
        System.out.println("17: " + (1.0/12.0+1.0/12.0*1.0/11.0*5.0+1.0/12.0*1.0/11.0*1.0/10.0*4.0));
        System.out.println("18: " + (1.0/12.0+1.0/12.0*1.0/11.0*5.0+1.0/12.0*1.0/11.0*1.0/10.0*8.0));


    }

    public String bestMove(){
        //return Engine.bestMove();
        return "";
    }
}
