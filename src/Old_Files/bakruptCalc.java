package Andere.Eigene.Blackjack;

        import java.math.BigInteger;

public class bakruptCalc {

    public static void main(String[] args) {

        System.out.println(riskOfBancruptcy(10, 110, 2, 1));
        double x = ((1-0.45*0.45)*0.45*0.45*0.45*0.55*4 + 0.45*0.45)*100;
        System.out.println(x);
    }

    public static int failAttempts (int games, int startbudget, int betvalue) {
        int maxloss;
        startbudget = startbudget - (startbudget%betvalue);
        maxloss = (startbudget/betvalue) + (games - startbudget/betvalue)/2 + (games - startbudget/betvalue)%2;
        return maxloss;
    }

    public static double riskOnAttempt (int games, double quote, int startbudget, int betvalue) {
        int x = failAttempts(games, startbudget, betvalue);
        long a [] = {1, 1};
        for (int i = 0; i < x; i++) {
            a[0] *= (games-i);
            a[1] *= (i+1);
        }
        double risk = (Math.pow((1-quote/200), x) * (Math.pow(quote/200, games-x))) * (a[0] / a[1]);
        //System.out.println("games " + games + "; failAttempts " + x);
        System.out.println(1-quote/200 + "^" + x + " * " + quote/200 + "^" + (games-x) + " * " + a[0] + "/" + a[1] + " = " + risk*100);
        return risk;
    }

    public static double riskOfBancruptcy (int games, double quote, int startbudget, int betvalue) {
        if (startbudget - betvalue < 0 || games < startbudget/betvalue || betvalue < 0 || startbudget < 0) {
            return -1;
        }
        double risk = 0;
        int x = startbudget/betvalue;
        for (int i = x; i <= games; i++) {
            if(startbudget%2 == i%2) {
                risk += (1-risk) * riskOnAttempt(i, quote, startbudget, betvalue);
                System.out.println(risk*100 + " (" + i + " games)");
            }
        }
        return risk*100;

    }

}
