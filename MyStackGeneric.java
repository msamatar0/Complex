
import java.util.InputMismatchException;

/**
 *
 * @author msamatar0
 * @param <E>
 */
abstract class MyStackGeneric<E> extends java.util.ArrayList<E>{
    protected abstract E newElement(String elem) throws NumberFormatException;
    public abstract E plus(E m, E n);
    public abstract E minus(E m, E n);
    public abstract E multiply(E m, E n);
    protected void push(E o){
        add(o);
    }
    protected E pop(){
        if(isEmpty()) return null;
        E n = get(size() - 1);
        remove(size() - 1);
        return n;
    }
    public E binaryOperator(String operator) throws ArithmeticException, InputMismatchException{
        E o, o1, o2;
        switch(operator){
            case "+":
                o1 = pop();
                o2 = pop();
                o = plus(o2, o1);
                push(o);
                break;
            case "-":
                o1 = pop();
                o2 = pop();
                o = minus(o2, o1);
                push(o);
                break;
            case "*":
                o1 = pop();
                o2 = pop();
                o = multiply(o2, o1);
                push(o);
                break;
            default:
                if(operator.length() > 1)
                    throw new InputMismatchException("InputMismatchException too long: " + operator);
                o = null;
                break;
        }
        return o;
    }
    public String processIO(java.util.Scanner in, java.io.PrintStream out){
        StringBuilder sb = new StringBuilder();
        
        while(in.hasNext()){
            String s = in.next();            
            try{
                E o = newElement(s);
                push(o);
                sb.append(o);
            }
            catch(NumberFormatException e){
                if(s.equals("p")){
                    sb.append(this);
                    out.println(this);
                }
                else
                    try{
                        sb.append(binaryOperator(s));
                    }
                    catch(InputMismatchException ie){
                        String ex = ie.getMessage();
                        sb.append(ex);
                        out.println(ex);
                    }
            }
            sb.append(' ');
        }
        return sb.toString();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        java.util.Iterator it = iterator();
        while(it.hasNext()){
            sb.append(it.next());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
