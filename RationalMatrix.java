/**
 * @author MohamedS
 */
public class RationalMatrix extends GenericMatrixMap<Rational>{
	@Override
	protected Rational newElement(String elem){
		return new Rational(elem);
	}
	
	@Override
	public Rational plus(Rational m, Rational n){
		return m.plus(n);
	}
	
	@Override
	public Rational minus(Rational m, Rational n){
		return m.minus(n);
	}
	
	@Override
	public Rational multiply(Rational m, Rational n){
		return m.multiply(n);
	}
	
	public static void main(String[] args){
		new RationalMatrix().processIO(new java.util.Scanner(System.in), System.out);
	}
}
