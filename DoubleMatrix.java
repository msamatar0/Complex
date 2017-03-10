
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author msamatar0
 */
public class DoubleMatrix extends GenericMatrixMap<Double>{
    @Override
    public Double plus(Double m, Double n){
        return m + n;
    }
    @Override
    public Double minus(Double m, Double n){
        return m - n;
    }
    @Override
    public Double multiply(Double m, Double n){
        return m * n;
    }
    @Override
    protected Double newElement(String elem){
        Double d = new Double(elem);
        push(d);
        return d;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
    }
}
