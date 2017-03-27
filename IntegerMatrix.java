import java.util.Scanner;

/**
 *
 * @author msamatar0
 */
public class IntegerMatrix extends GenericMatrixMap<Integer>{
    @Override
    protected Integer newElement(String elem){
        return new Integer(elem);
    }
    
    @Override
    public Integer plus(Integer m, Integer n){
        return m + n;
    }
    
    @Override
    public Integer minus(Integer m, Integer n){
        return m - n;
    }
    
    @Override
    public Integer multiply(Integer m, Integer n){
        return m * n;
    }
    
    public static void main(String[] args){
        new IntegerMatrix().processIO(new Scanner(System.in), System.out);
    }
}
