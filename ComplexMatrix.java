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
		new ComplexMatrix().processIO(new java.util.Scanner(System.in), System.out);
	}	
}
