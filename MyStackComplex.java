/**
 *
 * @author msamatar0
 */
public class MyStackComplex extends MyStackGeneric<Complex>{
    @Override
    protected Complex newElement(String elem) throws NumberFormatException{
        return new Complex(elem);
    }
    
    @Override
    public Complex plus(Complex m, Complex n){
        return m.plus(n);
    }
    
    @Override
    public Complex minus(Complex m, Complex n){
        return n.minus(m);
    }
    
    @Override
    public Complex multiply(Complex m, Complex n){
        return m.multiply(n);
    }
    
    public static void main(String[] args){
        try{
            new MyStackComplex().processIO(new java.util.Scanner(System.in), System.out);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
