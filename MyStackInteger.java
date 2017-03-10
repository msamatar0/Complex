/**
 *
 * @author msamatar0
 */
public class MyStackInteger extends MyStackGeneric<Integer>{
    @Override
    protected Integer newElement(String elem){
        Integer i = new Integer(elem);
        push(i);
        return i;
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
        java.util.Scanner in = new java.util.Scanner(System.in);
        MyStackInteger stack = new MyStackInteger();
        while(in.hasNext()){
            try{
                stack.processIO(in, System.out);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
