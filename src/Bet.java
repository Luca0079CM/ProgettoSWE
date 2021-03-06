class Bet {
    private int homeStrenght;
    private int awayStrenght;
    private double quote1, quoteX, quote2;
    private static final int n = 10;
    private static final int max = 300;

    Bet(int homeStrenght, int awayStrenght){
        this.homeStrenght = homeStrenght;
        this.awayStrenght = awayStrenght;
        createBet();
    }

    private void createBet() {
        float pHome = (float)(homeStrenght+Match.homeBonus)/max;
        float pAway = (float)(awayStrenght+Match.homeBonus)/max;
        float qHome = 1 - pHome;
        float qAway = 1 - pAway;

        float[] probCasa = new float[n+1];
        float[] probTras = new float[n+1];
        float prob1 = 0;
        float probX = 0;
        float prob2 = 0;


        for(int i=0; i<=n; i++) {
            probCasa[i] = binomial(i, pHome, qHome);
            probTras[i] = binomial(i,pAway,qAway);
        }

        for (int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(i>j) {
                    prob1 += probCasa[i] * probTras[j];
                }else if(i<j){
                    prob2 += probCasa[i]*probTras[j];
                }else{
                    probX += probCasa[i]*probTras[j];
                }
            }
        }
        quote1 = 1.1/prob1;
        quoteX = 1.1/probX;
        quote2 = 1.1/prob2;
    }

    private float binomial(int k, float p, float q) {
        float prob= (float)(factorial(n) / (factorial(k)* factorial(n-k)) );
        for(int i=0;i<k;i++) {
            prob *= p;
        }
        for(int i=0;i<(n-k);i++) {
            prob *= q;
        }
        return prob;
    }

    private int factorial(int n) {
        if(n==0)
            return 1;
        else
            return(n* factorial(n-1));
    }

    double[] getQuotes(){
        double[] tmp = new double[3];
        tmp[0] = quote1;
        tmp[1] = quoteX;
        tmp[2] = quote2;
        return tmp;
    }
}

