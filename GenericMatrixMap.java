import java.io.*;
import java.util.*;

/**
 *
 * @author msamatar0
 * @param <E>
 */

abstract class GenericMatrixMap<E> extends MyStackGeneric<E>{
    private final TreeMap<String, E[][]> matrixMap = new TreeMap<>
            ((String a, String b) ->  -(a.length() - b.length() == 0? b.compareTo(a) : b.length() - a.length()) );

    @Override
    public abstract E plus(E m, E n);

    @Override
    public abstract E minus(E m, E n);

    @Override
    public abstract E multiply(E m, E n);

    @Override
    public String processIO(Scanner in, PrintStream out){
        StringBuilder sb = new StringBuilder();
        String m, n, r, s;
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
                                if(matrixMap.containsKey(m))
                                    out.println("Print Matrix with Key: " + m + "\n" + printMatrix(get(m)) + "\n");
                                else
                                    out.println("Cannot Print Matrix " + m + " it does not exists.");
                                //sb.append(printMatrix(get(m)));
                                break;
                            case "Plus":
                                m = in.next();
                                n = in.next();
                                r = in.next();
                                put(r, plusMatrix(get(m), get(n)));
                                if(result)
                                    out.println("Print Result " + m +  '+' + n + '=' + r + "\n"
                                                + printResult(get(m), get(n), get(r), '+'));
                                break;
                            case "Minus":
                                m = in.next();
                                n = in.next();
                                r = in.next();
                                put(r, minusMatrix(get(m), get(n)));
                                if(result)
                                    out.println("Print Result " + m +  '-' + n + '=' + r + "\n"
                                            + printResult(get(m), get(n), get(r), '-'));
                                break;
                            case "Multiply":
                                m = in.next();
                                n = in.next();
                                r = in.next();
                                put(r, multiplyMatrix(get(m), get(n)));
                                if(result)
                                    out.println("Print Result " + m +  '-' + n + '=' + r + "\n"
                                            + printResult(get(m), get(n), get(r), '-'));
                                break;
                            case "PrintResult":
                                result = in.nextBoolean();
                                break;
                            case "Sum":
                                m = in.next();
                                out.println("Sum of " + m + " is " + sumMatrix(get(m)));
                                sb.append(sumMatrix(get(m)));
                                break;
                            case "Determinant":
                                m = in.next();
                                out.println("Determinant of " + m + " is " + determinant(get(m)));
                                sb.append(determinant(get(m)));
                                break;
                            default:
                                throw new InputMismatchException("unknown command: " + s + " after Matrix");
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
                                out.println("Matrix Keys: " + matrixMap.keySet());
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
                                out.println("First Key: " + matrixMap.firstKey());
                                sb.append("First Key: " + matrixMap.firstKey());
                                break;
                            case "LastKey":
                                out.println("Last Key: " + matrixMap.lastKey());
                                sb.append("Last Key: " + matrixMap.lastKey());
                                break;
                            case "Exists":
                                m = in.next();
                                out.println("Matrix " + m + " does" + (matrixMap.containsKey(m)? " " : " not ") + "exist.");
                                sb.append(matrixMap.containsKey(m));
                                break;
                            case "Remove":
                                m = in.next();
                                if(!matrixMap.containsKey(m))
                                    out.println("Cannot remove " + m + " not in TreeMap");
                                else{
                                    out.println("Removing " + m);
                                    matrixMap.remove(m);
                                }
                                break;
                            default:
                                throw new InputMismatchException("InputMismatchException unknown command: " + s + " after TreeMap");
                        }
                        break;
                    default:
                        sb.append(super.processIO(new Scanner(s), out));
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

    protected E[][] createMatrix(Scanner in){
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
            sb.append(String.format("  %" + l + "s", r[i]));
        return sb.append("|").toString();
    }

    private String[] sprintMatrix(E[][] m){
        int longest = m[0][0].toString().length();
        for(int i = 0; i < m.length; ++i)
            for(int j = 0; j < m[i].length; ++j)
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
        for(int i = 0; i < rows.length; ++i)
            sb.append(rows[i]).append(i == rows.length - 1? "" : "\n");
        return sb.toString();
    }

    public String printResult(E[][] m1, E[][] m2, E[][] m3, char op){
        String rows1[] = sprintMatrix(m1),
            rows2[] = sprintMatrix(m2),
            rows3[] = sprintMatrix(m3);
        StringBuilder expression = new StringBuilder();
        int height = Math.max(m1.length, m2.length);
        for(int i = 0; i < height; ++i)
            expression.append(rows1[i])
                      .append(i == height / 2? ("  " + op + "  ") : "     ")
                      .append(rows2[i]).append(i == height / 2? ("  =  ") : "     ")
                      .append(rows3[i]).append('\n');
        return expression.toString();
    }

    //Generic ver. of Professor Java determinant function
    //Source: (http://professorjava.weebly.com/matrix-determinant.html)
    public E determinant(E[][] matrix){
        E sum = newElement("0"), s;
        switch(matrix.length){
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
