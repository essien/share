
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Nov 21, 2015 | 1:21:46 PM
 * <pre>
 * <u>Description</u>
 *
 * </pre>
 *
 * @author Essiennta Emmanuel (colourfulemmanuel@gmail.com)
 */
public class CustomerPrize{
    private final int[] p;
    private final int[] v;
    private final int[] w;
    private final int[] pid;
    final static int MAX_VOL = 45 * 30 * 35;

    public CustomerPrize(int[] p, int[] v, int[] w, int[] pid){
        this.p = p;
        this.v = v;
        this.w = w;
        this.pid = pid;
    }

    void solve(){
        int[] dp = new int[MAX_VOL + 1];
        int[] sumpid = new int[MAX_VOL + 1];
        int[] sumw = new int[MAX_VOL + 1];
        for (int i = 0; i < pid.length; i++)
            for (int vol = MAX_VOL; vol >= 1; vol--) {
                if (vol - v[i] >= 0 && dp[vol - v[i]] + p[i] > dp[vol]) {
                    dp[vol] = dp[vol - v[i]] + p[i];
                    sumpid[vol] = sumpid[vol - v[i]] + pid[i];
                    sumw[vol] = sumw[vol - v[i]] + w[i];
                }
                if (vol - v[i] >= 0 && dp[vol - v[i]] + p[i] == dp[vol] && sumw[vol-v[i]] + w[i] < sumw[vol]){
                    sumpid[vol] = sumpid[vol - v[i]] + pid[i];
                    sumw[vol] = sumw[vol - v[i]] + w[i];
                }
            }
        System.out.println("sumpid: "+ sumpid[MAX_VOL]);
    }
    
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("/home/essiennta/Downloads/products.csv"));
        sc.useDelimiter(",|\\s+");
        int LIMIT = 20_000;
        int[] pid = new int[LIMIT];
        int[] v = new int[LIMIT];
        int[] w = new int[LIMIT];
        int[] p = new int[LIMIT];
        for (int i = 0; i < LIMIT; i++){
            pid[i] = sc.nextInt();
            p[i] = sc.nextInt();
            v[i] = sc.nextInt() * sc.nextInt() * sc.nextInt();
            w[i] = sc.nextInt();
        }
        new CustomerPrize(p, v, w, pid).solve();
        sc.close();
    }
}
