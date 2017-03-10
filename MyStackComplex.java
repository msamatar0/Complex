/**
 *
 * @author msamatar0
 */
public class MyStackComplex extends MyStackGeneric<Complex>{
    @Override
    protected Complex newElement(String elem){
        Complex c = new Complex(elem);
        push(c);
        return c;
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
        new MyStackComplex().processIO(new java.util.Scanner(System.in), System.out);
    }

}

