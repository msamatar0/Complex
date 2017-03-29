import java.util.Scanner;

/**
 *
 * @author msamatar0
 */
public class Exercise4_17{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt() % 16;
        for(int i = 1; i <= n; ++i){
            for(int k = n - i; k > 0; --k)
                System.out.print("   ");
            for(int j = 1; j <= i; ++j)
                System.out.printf("%3d", j);
            for(int j = i - 1; j > 0; --j)
                System.out.printf("%3d", j);
            System.out.println();
        }
    }
}
