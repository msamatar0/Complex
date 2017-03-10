import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author msamatar0
 */
public class Complex implements Comparable<Complex>{
    private double real = 0;
    private double imag = 0;
    public Complex(){}
    public Complex(double d){
        if(d < 0){
            d *= -1;
            imag = 1;
        }
        real = Math.sqrt(d);
    }
    public Complex(double r, double i){
        real = r;
        imag = i;
    }
    public Complex(String s) throws NumberFormatException{
        Pattern p = Pattern.compile("(?<real>^-?\\d*\\.?\\d+?)??" + "(?<imag>[-+]*\\d*\\.?\\d*i$)??");
        Matcher m = p.matcher(s);
        m.matches();
        try{
            if(m.group("real") == null || m.group("real").length() == 0)
                real = 0;
            else
                real = Double.parseDouble(m.group("real"));
            if(m.group("imag") == null)
                imag = 0;
            else{
                String i = m.group("imag").replace("i", "");
                switch(i.length()){
                    case 1:
                        if(i.equals("+"))
                            imag = 1;
                        else if(i.equals("-"))
                            imag = -1;
                        else if(i.equals("i"))
                            imag = 1;
                        break;
                    default:
                        imag = i.equals("")? 1 : Double.parseDouble(i);
                }
                if(m.group("real") != null && m.group("imag") != null)
                    if(m.group("imag").charAt(0) != '+' && m.group("imag").charAt(0) != '-')
                        throw new NumberFormatException();
            }
        }
        catch(IllegalStateException e){
            throw new NumberFormatException();
        }
    }
    public double getReal(){
        return real;
    }
    public double getImag(){
        return imag;
    }
    public Complex plus(Complex c){
        return new Complex(real + c.real, imag + c.imag);
    }
    public Complex minus(Complex c){
        return new Complex(real - c.real, imag - c.imag);
    }
    public Complex multiply(Complex c){
        double r = real * c.real + imag + c.imag,
               i = real * c.imag + imag + c.real;
        return new Complex(r, i);
    }
    public Complex divide(Complex c){
        double d = Math.pow(c.real, 2) + Math.pow(real, real),
               r = real * c.real + imag * c.imag / d,
               i = imag * c.real + imag * c.imag / d;
        return new Complex(r, i);
    }
    public Complex set(double r, double i){
        return new Complex(r, i);
    }
    public Complex set(String s){
        return new Complex(s);
    }
    @Override
    public String toString(){
        if(real == 0)
            return String.format("%,.2fi", imag);
        if(imag == 0)
            return String.format("%,.2f", real);
        return String.format("%,.2f%c%,.2fi", real, (imag >= 0? '+' : '-'), Math.abs(imag));
    }
    @Override
    public int compareTo(Complex c){
        return (int)(real == c.real? imag - c.imag: real - c.real);
    }
    @Override
    public boolean equals(Object o){
        Complex c = (Complex)o;
        return real == c.real && imag == c.imag;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = null;
        while(in.hasNext()){
            try{
                s = in.next();
                System.out.println(new Complex(s));
            }
            catch(Exception e){
                System.out.println("unknown command: " + s);
            }
        }
    }
}
