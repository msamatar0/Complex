import java.io.*;
import java.util.*;

/**
 *
 * @author msamatar0
 * @param <E>
 */

public abstract class GenericMatrixMap<E> extends MyStackGeneric<E>{
    TreeMap<String, E[][]> tree = new TreeMap<>((String a, String b) -> a.compareTo(b));
    @Override
    public abstract E plus(E m, E n);
    @Override
    public abstract E minus(E m, E n);
    @Override
    public abstract E multiply(E m, E n);
    @Override
    public String processIO(Scanner in, PrintStream out){
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()){
            switch(in.next().toLowerCase()){
                case "matrix":
                    switch(in.next().toLowerCase()){
                        case "create":
                            tree.put(in.next(), createMatrix(in));
                            break;
                    }
                    break;
                default:
                    sb.append(super.processIO(in, out));
            }
        }
        return sb.toString();   
    }
    public E[][] plusMatrix(E[][] m, E[][] n){
        if ((m.length != n.length) || (m[0].length != n[0].length))
          throw new RuntimeException("matrices do not have same size");
        
        E[][] result = (E[][])new Object[m.length][m[0].length];
        
        for (int i = 0; i < result.length; i++)
          for (int j = 0; j < result[i].length; j++)
            result[i][j] = plus(m[i][j], n[i][j]);
        
        return result;
    }
    public E[][] minusMatrix(E[][] m, E[][] n){
        if ((m.length != n.length) || (m[0].length != n[0].length))
          throw new RuntimeException("matrices do not have same size");
        
        E[][] result = (E[][])new Object[m.length][m[0].length];
        
        for (int i = 0; i < result.length; i++)
          for (int j = 0; j < result[i].length; j++)
            result[i][j] = minus(m[i][j], n[i][j]);
        
        return result;
    }
    public E[][] multiplyMatrix(E[][] m, E[][] n){
        if (m[0].length != n.length)
          throw new RuntimeException("matrices do not have compatible size");

        E[][] result = (E[][])new Object[m.length][n[0].length];

        for (int i = 0; i < result.length; i++)
          for (int j = 0; j < result[0].length; j++)
            for (int k = 0; k < m[0].length; k++)
              result[i][j] = plus(result[i][j], multiply(m[i][k], n[k][j]));

        return result;
    }
    protected final E[][] createMatrix(Scanner in){
        E[][] matrix = (E[][])new Object[1][1];
        for(int i = 0; i < 0; ++i)
            for(int j = 0; j < i; ++j)
                matrix[i][j] = newElement(in.next());
        return matrix;
    }
    public void printResult(Number[][] m1, Number[][] m2, Number[][] m3, char op) {
        for (int i = 0; i < m1.length; i++) {
        for (int j = 0; j < m1[0].length; j++)
          System.out.printf(" " + m1[i][j]);

        if (i == m1.length / 2)
          System.out.printf("  " + op + "  ");
        else
          System.out.printf("     ");

        for (int j = 0; j < m2.length; j++)
          System.out.printf(" " + m2[i][j]);

        if (i == m1.length / 2)
          System.out.printf("  =  ");
        else
          System.out.printf("     ");

        for (int j = 0; j < m3.length; j++)
          System.out.printf(m3[i][j] + " ");

        System.out.println();
      }
    }
    public E determinant(E[][] m, int n){
        E det = newElement("0");
        E[][] temp = (E[][])new Object[n][n];
        switch(m.length){
            case 1:
                return m[0][0];
            case 2:
                return minus(multiply(m[0][0], m[1][1]), multiply(m[0][1], m[1][0]));
            default:
                for(int idx = 0; idx < n; ++idx){
                    int a = 0, b = 0;
                    for(int i = 0; i < n; ++i)
                        for(int j = 0; j < n; ++j){
                            if(j == idx)
                                continue;
                            temp[a][b] = m[i][j];
                            b++;
                            if(b == n - 1){
                                a++;
                                b = 0;
                            }
                        }                            
                det = plus(det,
                        multiply(m[0][idx],
                                (idx % 2 == 0? minus(newElement("0"), determinant(temp, temp.length - 1)):
                                        determinant(temp, temp.length - 1))));
                }
                break;
        }
        return det;
    }
}
