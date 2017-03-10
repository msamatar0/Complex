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
        return m.minus(n);
    }
    @Override
    public Complex multiply(Complex m, Complex n){
        return m.multiply(n);
    }
    public static void main(String[] args){
        String data = "2 3 2+3i 2-3i i + + p 2 3.5+i * p 1.0i -2.0i + p 2.0 3i + * p 3 + + + - p";
        new MyStackComplex().processIO(new java.util.Scanner(data), System.out);
    }
}
