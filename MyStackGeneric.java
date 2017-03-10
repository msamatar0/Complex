/**
 *
 * @author msamatar0
 * @param <E>
 */
abstract class MyStackGeneric<E> extends java.util.ArrayList<E>{
    protected abstract E newElement(String elem);
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
    public E binaryOperator(String operator) throws ArithmeticException{
        E o;
        if(size() < 2)
            throw new ArithmeticException("ArithmeticException need two operands");
        switch(operator){
            case "+":
                o = plus(pop(), pop());
                break;
            case "-":
                o = minus(pop(), pop());
                break;
            case "*":
                o = multiply(pop(), pop());
                break;
            default:
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
                sb.append(newElement(s));
            }
            catch(ArithmeticException e){
                if(s.equals("p")){
                    sb.append(this);
                    out.println(this);
                }
                else
                    sb.append(binaryOperator(s));
            }
            catch(Exception e){
                out.println(e.getMessage());
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
