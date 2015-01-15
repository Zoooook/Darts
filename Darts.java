public class Darts {
    public static void main(String[] args){

        int[] zookRings = new int[]{2,2,24,1,1};

        System.out.println(Math.sqrt(simpleEM(zookRings,100,100)));
    }

    // distances in mm: center (0), inner bulls, outer bulls, inner triple, outer triple, inner double, outer double, edge of board, infinity (-1)
    public static final double[] R = new double[]{0,7,17,96,107,160,169,224,-1};

    public static double simpleEM(int[] rings, double sInit, int numIter){
        double s = sInit;
        for(int i=0; i<numIter; i++)
            s = simpleStep(rings,s);
        return s;
    }

    private static double simpleStep(int[] rings, double s){
        double[] a = new double[R.length-1];
        for(int i=0; i<a.length; i++)
            a[i] = ((R[i]*R[i]+2*s)*Math.exp(-R[i]*R[i]/(2*s)) - (R[i+1]==-1 ? 0 : (R[i+1]*R[i+1]+2*s)*Math.exp(-R[i+1]*R[i+1]/(2*s)))) /
                   (Math.exp(-R[i]*R[i]/(2*s)) - (R[i+1]==-1 ? 0 : Math.exp(-R[i+1]*R[i+1]/(2*s))));

        double e = 0;
        int n = 0;
        for(int i=0; i<rings.length; i++){
            e += rings[i]*a[i];
            n += rings[i];
        }
        return e/(2*n);
    }
}
