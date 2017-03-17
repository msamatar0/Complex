import java.io.*;
import java.util.*;

/**
 *
 * @author msamatar0
 * @param <E>
 */

abstract class GenericMatrixMap<E> extends MyStackGeneric<E>{
    private final TreeMap<String, E[][]> matrixMap = new TreeMap<>
        ((String a, String b) -> { return a.length() - b.length() == 0? b.compareTo(a) : b.length() - a.length(); } );

    @Override
    public abstract E plus(E m, E n);

    @Override
    public abstract E minus(E m, E n);

    @Override
    public abstract E multiply(E m, E n);

    @Override
    public String processIO(Scanner in, PrintStream out){
        StringBuilder sb = new StringBuilder();
        String m, s;
        boolean result = false;
        while(in.hasNext()){
            try{
                s = in.next();
                switch(s){
                    case "Matrix":
                        s = in.next();
                        switch(s){
                            case "Create":
                                matrixMap.put(in.next(), createMatrix(in));
                                break;
                            case "Print":
                                m = in.next();
                                out.println(printMatrix(get(m)));
                                sb.append(printMatrix(get(m)));
                                break;
                            case "Plus":
                                if(result)
                                    out.println(Arrays.toString(plusMatrix(get(in.next()), get(in.next()))));
                                //sb.append(Arrays.toString(put(in.next(), plusMatrix(get(in.next()), get(in.next())))));
                                break;
                            case "Minus":
                                if(result)
                                    out.println(Arrays.toString(minusMatrix(get(in.next()), get(in.next()))));
                                //sb.append(Arrays.toString(put(in.next(), minusMatrix(get(in.next()), get(in.next())))));
                                break;
                            case "Multiply":
                                if(result)
                                    out.println(Arrays.toString(multiplyMatrix(get(in.next()), get(in.next()))));
                                sb.append(Arrays.toString(put(in.next(), multiplyMatrix(get(in.next()), get(in.next())))));
                                break;
                            case "PrintResult":
                                result = true;
                                break;
                            case "Sum":
                                out.println(sumMatrix(get(in.next())));
                                sb.append(sumMatrix(get(in.next())));
                                break;
                            case "Determinant":
                                out.println(determinant(get(in.next())));
                                sb.append(determinant(get(in.next())));
                                break;
                            default:
                                throw new InputMismatchException("unknown command " + s + " after Matrix");
                        }
                        break;
                    case "TreeMap":
                        s = in.next();
                        switch(s){
                            case "Size":
                                out.println("Matrix Count: " + matrixMap.size());
                                sb.append(matrixMap.size());
                                break;
                            case "Keys":
                                out.println("Matrix Keys: " + matrixMap.descendingKeySet());
                                sb.append(matrixMap.keySet());
                                break;
                            case "HeadMap":
                                m = in.next();
                                out.println("Matrices Less than " + m + ": " +  matrixMap.headMap(m).keySet());
                                sb.append(matrixMap.headMap(m).keySet());
                                break;
                            case "TailMap":
                                m = in.next();
                                out.println("Matrices greater than " + m + ": " + matrixMap.tailMap(m).keySet());
                                sb.append(matrixMap.tailMap(m).keySet());
                                break;
                            case "DescendingKeySet":
                                out.println(matrixMap.descendingKeySet());
                                sb.append(matrixMap.descendingKeySet());
                                break;
                            case "FirstKey":
                                out.println(matrixMap.firstKey());
                                sb.append(matrixMap.firstKey());
                                break;
                            case "LastKey":
                                out.println(matrixMap.lastKey());
                                sb.append(matrixMap.lastKey());
                                break;
                            case "Exists":
                                m = in.next();
                                out.println("Matrix " + m + " does" + (matrixMap.containsKey(m)? " " : " not ") + "exist");
                                sb.append(matrixMap.containsKey(m));
                                break;
                            case "Remove":
                                matrixMap.remove(in.next());
                                break;
                            default:
                                throw new InputMismatchException("InputMismatchException unknown command " + s + " after TreeMap");
                        }
                        break;
                    default:
                        sb.append(s = super.processIO(new Scanner(s), out));
                }
            }
            catch(Exception e){
                sb.append(e.getMessage());
                out.println(e.getMessage());
            }
        }
        return sb.toString();
    }

    public E[][] get(String key){
        return matrixMap.get(key);
    }

    public E[][] put(String k, E[][] v){
        return matrixMap.put(k, v);
    }

    protected final E[][] createMatrix(Scanner in){
        int row = in.nextInt(), col = in.nextInt();
        E[][] matrix = (E[][])new Object[row][col];
        for(int i = 0; i < row; ++i)
            for(int j = 0; j < col; ++j)
                matrix[i][j] = newElement(in.next());
        return matrix;
    }
    
    public E sumMatrix(E[][] matrix){
        E sum = newElement("0");
        for(int i = 0; i < matrix.length; ++i)
            for(int j = 0; j < matrix[0].length; ++j)
                sum = plus(sum, matrix[i][j]);
        return sum;
    }
    
    public E[][] plusMatrix(E[][] m, E[][] n){
        if((m.length != n.length) || (m[0].length != n[0].length))
            throw new RuntimeException("matrices do not have same size");

        E[][] result = (E[][])new Object[m.length][m[0].length];

        for(int i = 0; i < result.length; i++)
            for(int j = 0; j < result[i].length; j++)
                result[i][j] = plus(m[i][j], n[i][j]);

        return result;
    }

    public E[][] minusMatrix(E[][] m, E[][] n){
        if((m.length != n.length) || (m[0].length != n[0].length))
            throw new RuntimeException("matrices do not have same size");

        E[][] result = (E[][])new Object[m.length][m[0].length];

        for(int i = 0; i < result.length; i++)
            for(int j = 0; j < result[i].length; j++)
                result[i][j] = minus(m[i][j], n[i][j]);

        return result;
    }

    public E[][] multiplyMatrix(E[][] m, E[][] n){
        if(m[0].length != n.length)
            throw new RuntimeException("matrices do not have compatible size");

        E[][] result = (E[][])new Object[m.length][n[0].length];

        for(int i = 0; i < result.length; i++)
            for(int j = 0; j < result[0].length; j++)
                for(int k = 0; k < m[0].length; k++)
                    result[i][j] = plus(result[i][j], multiply(m[i][k], n[k][j]));

        return result;
    }

    private String sprintRow(E[] r, int l){
        StringBuilder sb = new StringBuilder("|");
        for(int i = 0; i < r.length; ++i)
            sb.append(String.format("%" + l + 2 + "s", r[i]));
        return sb.append('|').toString();
    }

    private String[] sprintMatrix(E[][] m){
        int longest = m[0][0].toString().length();
        for(int i = 0; i < m.length; ++i)
            for(int j = 0; j < m[0].length; ++j)
                if(m[i][j].toString().length() > longest)
                    longest = m[i][j].toString().length();
        String[] rows = new String[m.length];
        for(int i = 0; i < rows.length; ++i)
            rows[i] = sprintRow(m[i], longest);
        return rows;
    }
    
    public String printMatrix(E[][] m){
        String rows[] = sprintMatrix(m);
        StringBuilder sb = new StringBuilder();
        for(String i : rows)
            sb.append(i).append('\n');
        return sb.toString();
    }

    public String printResult(E[][] m1, E[][] m2, E[][] m3, char op){
        String rows1[] = sprintMatrix(m1),
            rows2[] = sprintMatrix(m2),
            rows3[] = sprintMatrix(m3);
        StringBuilder expression = new StringBuilder();
        int height = Math.max(m1.length, m2.length);
        for(int i = 0; i < height; ++i)
            expression.append(rows1[i] +
                (i == height / 2? ("  " + op + "  ") : "     ") +
                rows2[i] +
                (i == height / 2? ("  =  ") : "     ") +
                rows3[i] + '\n');
        return expression.toString();
    }
    
    //Generic ver. of Professor Java determinant function
    public E determinant(E[][] matrix){
        E sum = newElement("0"), s;
        switch (matrix.length) {
            case 1:
                return matrix[0][0];
            case 2:
                return minus(multiply(matrix[0][0], matrix[1][1]), multiply(matrix[0][1], matrix[1][0]));
            case 3:
                return minus(
                        plus(multiply(matrix[0][0], multiply(matrix[1][1], matrix[2][2])),
                                plus(multiply(matrix[0][1], multiply(matrix[1][2], matrix[2][0])),
                                        multiply(matrix[0][2], multiply(matrix[1][0], matrix[2][1])))),
                        minus(multiply(matrix[0][2], multiply(matrix[1][1], matrix[2][0])),
                                minus(multiply(matrix[0][0], multiply(matrix[2][2], matrix[2][1])),
                                        multiply(matrix[0][1], multiply(matrix[1][0], matrix[2][2]))))
                            );
            default:
                for(int i = 0; i < matrix.length; i++){
                    E[][] smaller = (E[][])new Object[matrix.length-1][matrix.length-1];
                    for(int a = 1; a < matrix.length; ++a){
                        for(int b = 0; b < matrix.length; ++b){
                            if(b < i)
                                smaller[a - 1][b] = matrix[a][b];
                            else
                                smaller[a - 1][b - 1] = matrix[a][b];
                        }
                    }
                    if(i % 2 == 0)
                        s = newElement("1");
                    else
                        s = newElement("-1");
                    sum = plus(sum, multiply(s, multiply(matrix[0][i], (determinant(smaller)))));
                }   break;
        }
        return sum;
    }

    @Override
    public String toString(){
        return matrixMap.toString();
    }
}