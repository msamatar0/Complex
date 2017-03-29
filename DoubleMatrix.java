/**
 * @author msamatar0
 */
public class DoubleMatrix extends GenericMatrixMap<Double>{
	@Override
	protected Double newElement(String elem){
		return new Double(elem);
	}
	
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
	
	public static void main(String[] args){
		new DoubleMatrix().processIO(new java.util.Scanner(System.in), System.out);
	}
	
	public static String data = "Matrix Create $x 2 2 2.0 3.0 4.0 5.0\n" +
					"Matrix Create $abc 3 2 2.0 3.0 4.0 2.0 3.0 5.0\n" +
					"Matrix Create $tuv 2 2 2.0 3.0 4.0 5.0\n" +
					"Matrix Create $x1 2 2 1.0 3.0 2.0 4.0\n" +
					"Matrix Create $y 2 2 2.0 2.0 -2.0 -2.0\n" +
					"Matrix Create $z 1 2 1.0 1.0\n" +
					"TreeMap Keys TreeMap InfoTreeMap DescendingKeySet TreeMap Size \n" +
					"TreeMap HeadMap $x1 TreeMap TailMap $x1 TreeMap Exists $x1 \n" +
					"TreeMap Exists $x2 TreeMap FirstKey TreeMap LastKey \n" +
					"TreeMap Remove $x1 TreeMap TailMap $x1 TreeMap Remove $x1";
}
