/**
 *
 * @author msamatar0
 */
public class MyStackRational extends MyStackGeneric<Rational>{
    @Override
    protected Rational newElement(String elem){
        Rational r = new Rational(elem);
        push(r);
        return r;
    }
    @Override
    public Rational plus(Rational m, Rational n){
        return m.plus(n);
    }
    @Override
    public Rational minus(Rational m, Rational n){
        return m.minus(n);
    }
    @Override
    public Rational multiply(Rational m, Rational n){
        return m.multiply(n);
    }
    public static void main(String[] args){
        String data = "1/2 1/-2 -1/2 + + p 1/2 - 2/4 3/6 7/8 + + * p";
        new MyStackRational().processIO(new java.util.Scanner(data), System.out);
    }
}
