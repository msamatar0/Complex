import java.io.*;
import java.util.*;

/**
 *
 * @author msamatar0
 * @param <E>
 */

abstract class GenericMatrixMap<E> extends MyStackGeneric<E>{
    private final TreeMap<String, E[][]> matrixMap = new TreeMap<>
        ((String a, String b) ->  -(a.length() - b.length() == 0? b.compareTo(a) : b.length() - a.length()));
    
    private boolean result = false;

    @Override
    public String processIO(Scanner in, PrintStream out){
        StringBuilder sb = new StringBuilder();
        String m, n, r, s;
        while(in.hasNext()){
            try{
                s = in.next();
                switch(s){
                    case "Matrix":
                    case "matrix":
                    case "m":
                        s = in.next();
                        switch(s){
                            case "Create":
                            case "create":
                                m = in.next();
                                matrixMap.put(m, createMatrix(in));
                                break;
                            case "Print":
                            case "print":
                                m = in.next();
                                if(matrixMap.containsKey(m))
                                    out.println("Print Matrix with Key: " + m + "\n" + printMatrix(get(m)) + "\n");
                                else
                                    out.println("Cannot Print Matrix " + m + " it does not exists.");
                                break;
                            case "Plus":
                            case "plus":
                                m = in.next();
                                n = in.next();
                                r = in.next();
                                expHelper(out, m, n, r, '+');
                                break;
                            case "Minus":
                            case "minus":
                                m = in.next();
                                n = in.next();
                                r = in.next();
                                expHelper(out, m, n, r, '-');
                                break;
                            case "Multiply":
                            case "multiply":
                                m = in.next();
                                n = in.next();
                                r = in.next();
                                expHelper(out, m, n, r, '*');
                                break;
                            case "PrintResult":
                                result = in.nextBoolean();
                                break;
                            case "Sum":
                            case "sum":
                                m = in.next();
                                out.println("Sum of " + m + " is " + sumMatrix(get(m)));
                                break;
                            case "Determinant":
                            case "determinant":
                            case "dt":
                                m = in.next();
                                if(get(m).length == get(m)[0].length)
                                    out.println("Determinant of " + m + " is " + determinant(get(m)));
                                else
                                    out.println("Failed on " + m);
                                break;
                            default:
                                throw new InputMismatchException("InputMismatchException unknown command: " + s + " after Matrix");
                        }
                        break;
                    case "TreeMap":
                    case "treemap":
                    case "tm":
                        s = in.next();
                        switch(s){
                            case "Size":
                            case "size":
                                out.println("Matrix Count: " + matrixMap.size());
                                sb.append(matrixMap.size());
                                break;
                            case "Keys":
                            case "keys":
                                out.println("Matrix Keys: " + matrixMap.keySet());
                                sb.append(matrixMap.keySet());
                                break;
                            case "HeadMap":
                            case "head":
                                m = in.next();
                                out.println("Matrices Less than " + m + ": " +  matrixMap.headMap(m).keySet());
                                sb.append(matrixMap.headMap(m).keySet());
                                break;
                            case "TailMap":
                            case "tail":
                                m = in.next();
                                out.println("Matrices greater than " + m + ": " + matrixMap.tailMap(m).keySet());
                                sb.append(matrixMap.tailMap(m).keySet());
                                break;
                            case "DescendingKeySet":
                            case "dks":
                                out.println(matrixMap.descendingKeySet());
                                sb.append(matrixMap.descendingKeySet());
                                break;
                            case "FirstKey":
                            case "first":
                                out.println("First Key: " + matrixMap.firstKey());
                                sb.append("First Key: ").append(matrixMap.firstKey());
                                break;
                            case "LastKey":
                            case "last":
                                out.println("Last Key: " + matrixMap.lastKey());
                                sb.append("Last Key: ").append(matrixMap.lastKey());
                                break;
                            case "Exists":
                            case "exists":
                                m = in.next();
                                out.println("Matrix " + m + " does" + (matrixMap.containsKey(m)? " " : " not ") + "exist.");
                                sb.append(matrixMap.containsKey(m));
                                break;
                            case "Remove":
                            case "remove":
                                m = in.next();
                                if(!matrixMap.containsKey(m))
                                    out.println("Cannot remove " + m + " not in TreeMap");
                                else{
                                    out.println("Removing " + m);
                                    matrixMap.remove(m);
                                }
                                break;
                            case "Info":
                            case "info":
                                out.println("Matrix Info:");
                                for(Object i : matrixMap.keySet().toArray())
                                    out.println(String.format("\t%s %d %d", (String)i, get((String)i).length, get((String)i)[0].length));
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
                out.println(e instanceof NullPointerException? "NullPointerException null" : e.getMessage());
            }
        }
        return sb.toString();
    }
    
    private void expHelper(PrintStream out, String m, String n, String r, char op){
        E[][] rm = null;
        try{
            switch(op){
                case '+':
                    rm = plusMatrix(get(m), get(n));
                    break;
                case '-':
                    rm = minusMatrix(get(m), get(n));
                    break;
                case '*':
                    rm = multiplyMatrix(get(m), get(n));
                    break;
            }
            put(r, rm);
            if(result)
                out.println("Print Result " + m +  " " + op + " " + n + " = " + r + "\n"
                            + printResult(get(m), get(n), get(r), op));
            }
        catch(NullPointerException nu){
            out.println("Failed on " + m + op + n);
            throw new NullPointerException("NullPointerException " + (get(m) == null? "first" : "second") + " matrix argument is null");
        }
        catch(RuntimeException e){
            out.println("Failed on " + m + op + n);
            throw new RuntimeException("ArithmeticException matrices do not have same size");
        }
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
            throw new RuntimeException();

        E[][] sum = (E[][])new Object[m.length][m[0].length];

        for(int i = 0; i < sum.length; i++)
            for(int j = 0; j < sum[i].length; j++)
                sum[i][j] = plus(m[i][j], n[i][j]);

        return sum;
    }

    public E[][] minusMatrix(E[][] m, E[][] n){
        if((m.length != n.length) || (m[0].length != n[0].length))
            throw new RuntimeException();

        E[][] difference = (E[][])new Object[m.length][m[0].length];

        for(int i = 0; i < difference.length; i++)
            for(int j = 0; j < difference[i].length; j++)
                difference[i][j] = minus(m[i][j], n[i][j]);

        return difference;
    }

    public E[][] multiplyMatrix(E[][] m, E[][] n){
        if(m[0].length != n.length)
            throw new RuntimeException();

        E[][] product = (E[][])new Object[m.length][n[0].length];
        
        for (E[] i : product)
            for (int j = 0; j < product[0].length; j++)
                i[j] = newElement("0");

        for(int i = 0; i < product.length; i++)
            for(int j = 0; j < product[0].length; j++)
                for(int k = 0; k < m[0].length; k++)
                    product[i][j] = plus(product[i][j], multiply(m[i][k], n[k][j]));

        return product;
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
            expression.append(i >= rows1.length? String.format("%" + rows1[rows1.length - 1].length() + "s", "") : rows1[i])
                      .append(i == m1.length / 2? ("  " + op + "  ") : "     ")
                      .append(i >= rows2.length? String.format("%" + rows2[rows2.length - 1].length() + "s", "") : rows2[i])
                      .append(i == m1.length / 2? ("  =  ") : "     ")
                      .append(i >= rows3.length? "" : rows3[i])
                      .append('\n');
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
                Object[] terms ={
                    multiply(matrix[0][0], multiply(matrix[1][1], matrix[2][2])),
                    multiply(matrix[0][1], multiply(matrix[1][2], matrix[2][0])),
                    multiply(matrix[0][2], multiply(matrix[1][0], matrix[2][1])),
                    multiply(matrix[0][2], multiply(matrix[1][1], matrix[2][0])),
                    multiply(matrix[0][0], multiply(matrix[1][2], matrix[2][1])),
                    multiply(matrix[0][1], multiply(matrix[1][0], matrix[2][2]))
                };
                return minus(plus((E)(terms[0]), plus((E)(terms[1]), (E)(terms[2]))),
                        plus((E)(terms[3]), plus((E)(terms[4]), (E)(terms[5]))));
            default:
                for(int i = 0; i < matrix.length; i++){
                    E[][] smaller = (E[][])new Object[matrix.length - 1][matrix.length - 1];
                    for(int a = 1; a < matrix.length; ++a){
                        for(int b = 0; b < matrix.length; ++b){
                            if(b < i)
                                smaller[a - 1][b] = matrix[a][b];
                            else if(b > i)
                                smaller[a - 1][b - 1] = matrix[a][b];
                        }
                    }
                    if(i % 2 == 0)
                        s = newElement("1");
                    else
                        s = newElement("-1");
                    sum = plus(sum, multiply(s, multiply(matrix[0][i], (determinant(smaller)))));
                }
                break;
        }
        return sum;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
