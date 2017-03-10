import java.util.InputMismatchException;

public class Rational implements Comparable<Rational>{
    private long numerator = 0, denominator = 0;
    
    public Rational(long n, long d){
        long g = gcd(n, d);
        numerator = (d > 0? 1 : -1) * n / g;
        denominator = Math.abs(d) / g;
    }
    public Rational(String s) throws NumberFormatException{
        long n = 0, d = 0;
        boolean one = s.matches("^-?\\d+$"),
                two = s.matches("^-?\\d+/-?\\d+$");
        //if(!one && !two)
            //throw new InputMismatchException("InputMismatchException unknown command: " + s);
        /*else*/ if(two){
            String part[] = s.split("/");
            n = Long.parseLong(part[0]);
            d = Long.parseLong(part[1]);
        }
        else if(one){
            n = Long.parseLong(s);
            d = 1;
        }
        else
            throw new NumberFormatException();
//throw new InputMismatchException("InputMismatchException unknown command: " + s);
        //if(d == 0)
            //throw new InputMismatchException("InputMismatchException too long: " + s);
        long g = gcd(n, d);
        numerator = (d > 0? 1 : -1) * n / g;
        denominator = Math.abs(d) / g;
    }
    public long getNumerator(){ return numerator; }
    public long getDenominator(){ return denominator; }
    public Rational plus(Rational r) {
        long n = numerator * r.denominator +
                 denominator * r.numerator;
        long d = denominator * r.denominator;
        return new Rational(n, d);
    }
    public Rational minus(Rational r){
        long n = r.numerator * denominator -
                 r.denominator * numerator;
        long d = denominator * r.denominator;
        return new Rational(n, d);
    }
    public Rational multiply(Rational r){
        long n = numerator * r.numerator;
        long d = denominator * r.denominator;
        return new Rational(n, d);
    }
    public Rational divide(Rational r){
        long n = numerator * r.denominator;
        long d = denominator * r.numerator;
        return new Rational(n, d);
    }
    public boolean equals(Rational r){
        return numerator == r.numerator && denominator == r.denominator;
    }
    public static Rational parseRational(String s){
        return new Rational(s);
    }
    private static long gcd(long n, long d){
        n = Math.abs(n);
        d = Math.abs(d);
        return d == 0? n : gcd(d, n % d);
    }
    @Override
    public int compareTo(Rational o){
        return 0;
    }
    @Override
    public String toString(){
        return numerator + (denominator == 1? "" : "/" + denominator);
    }
    public static void main(String[] args){
        try{
            System.out.println(
                new Rational(new java.util.Scanner(System.in).next())
            );
        }catch(InputMismatchException | ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}