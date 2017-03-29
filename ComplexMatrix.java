/**
 * @author MohamedS
 */
public class ComplexMatrix extends GenericMatrixMap<Complex>{
	@Override
	protected Complex newElement(String elem){
		return new Complex(elem);
	}
	
	@Override
	public Complex plus(Complex m, Complex n){
		return m.plus(n);
	}
	
	@Override
	public Complex minus(Complex m, Complex n){
		return m.minus(n);
	}
	
	@Override
	public Complex multiply(Complex m, Complex n){
		return m.multiply(n);
	}
	
	public static void main(String[] args){
		new ComplexMatrix().processIO(new java.util.Scanner(data), System.out);
	}
	
	static String data = "Matrix Create $x 2 2 39.00-28.00i 36.00-31.00i -74.00+44.00i -87.00-50.00i Matrix Create $y 2 2 -21.00-65.00i 88.00-31.00i -51.00+22.00i 94.00+13.00i Matrix Create $t 2 2 -27.00+17.00i -41.00-73.00i -28.00-64.00i 53.00-34.00i Matrix Create $x1 2 2 28.00+6.00i -80.00+61.00i -48.00+76.00i -53.00+78.00i Matrix Create $xy 2 2 -48.00-51.00i -99.00+23.00i 61.00+15.00i -80.00-52.00i Matrix Plus $x $y $z1 Matrix Multiply $x $y $x2 Matrix PrintResult true Matrix Minus $x $y $z TreeMap Size TreeMap Info TreeMap Keys Matrix Print $x2 Matrix PrintResult false Matrix Multiple $x2 $x1 $x3 Matrix Print $x3";
}
