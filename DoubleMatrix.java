import java.util.Scanner;

/**
 *
 * @author msamatar0
 */
public class DoubleMatrix extends GenericMatrixMap<Double>{
    @Override
    protected Double newElement(String elem){
        return new Double(elem);
    }
    @Override
    public Double plus(Double m, Double n){
        return m + n;
    }
    @Override
    public Double minus(Double m, Double n){
        return m - n;
    }
    @Override
    public Double multiply(Double m, Double n){
        return m * n;
    }
    public static void main(String[] args){
        System.out.println(new DoubleMatrix().processIO(new Scanner(System.in), System.out));
    }
}
