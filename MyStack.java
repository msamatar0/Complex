import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class MyStack extends ArrayList<Integer>{
    public void push(int n){
        add(n);
    }
    public int pop(){
        int n = get(size() - 1);
        remove(size() - 1);
        return n;
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        Iterator it = iterator();
        while(it.hasNext()){
            sb.append(it.next());
            sb.append(", ");
        }
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        MyStack ms = new MyStack();
        String s; 
        while(in.hasNext()){
            s = in.next();
            if(s.matches("-?\\d+"))
                ms.push(new Integer(s));
            else
                switch(s){
                    case "+":
			if(ms.size() < 2){
                            System.out.println("ArithmeticException need two operands");
                            continue;
                        }
                        int a = ms.pop(),
                            b = ms.pop();
                        ms.push(b + a);
                        break;
                    case "-":
			if(ms.size() < 2){
                            System.out.println("ArithmeticException need two operands");
                            continue;
                        }
                        int c = ms.pop(),
                            d = ms.pop();
                        ms.push(d - c);
                        break;
                    case "p":
                        System.out.println(ms);
                        break;
                    default:
                        System.out.println("InputMismatchException " +
                                           (s.length() > 1? "too long: ": "unknown command") + s);
                }
        }
    }
}
