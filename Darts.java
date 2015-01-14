public class Darts {
    public static void main(String[] args){
        int[] zookRings=new int[]{0};

        System.out.println(Math.sqrt(simpleEM(zookRings,100,100)));
    }

    // distances in mm
    public static final double R1 = 7; // distance to double bullseye
    public static final double R2 = 17; // distance to single bullseye
    public static final double R3 = 96; // distance to inside triple ring
    public static final double R4 = 107; // distance to outside triple ring
    public static final double R5 = 161; // distance to inside double ring
    public static final double R6 = 170; // distance to outside double ring
    public static final double R7 = 200; // distance to edge of board

    public static double simpleEM(int[] rings, double sInit, int numIter){
        double s = sInit;
        for(int i=0; i<numIter; i++)
            s = simpleStep(rings,s);
        return s;
    }

    private static double simpleStep(int[] rings, double s){
        double[] a = new double[8];
        a[0] = integNumerator(s,0,R1) / integDenominator(s,0,R1);
        a[1] = integNumerator(s,R1,R2) / integDenominator(s,R1,R2);
        a[2] = integNumerator(s,R2,R3) / integDenominator(s,R2,R3);
        a[3] = integNumerator(s,R3,R4) / integDenominator(s,R3,R4);
        a[4] = integNumerator(s,R4,R5) / integDenominator(s,R4,R5);
        a[5] = integNumerator(s,R5,R6) / integDenominator(s,R5,R6);
        a[6] = integNumerator(s,R6,R7) / integDenominator(s,R6,R7);
        a[7] = integNumerator(s,R7,-1) / integDenominator(s,R7,-1);

        int n = rings.length;
        double e = 0;
        for(int i=0; i<n; i++)
            e += a[rings[i]];
        return e/(2*n);
    }

    private static double integNumerator(double s, double r1, double r2){
        if(r2==-1)
            return (r1*r1+2*s)*Math.exp(-r1*r1/(2*s));
        else
            return (r1*r1+2*s)*Math.exp(-r1*r1/(2*s)) - (r2*r2+2*s)*Math.exp(-r2*r2/(2*s));
    }

    private static double integDenominator(double s, double r1, double r2){
        if(r2==-1)
            return Math.exp(-r1*r1/(2*s));
        else
            return Math.exp(-r1*r1/(2*s)) - Math.exp(-r2*r2/(2*s));
    }
}
