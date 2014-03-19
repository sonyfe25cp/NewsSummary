
import Jama.Matrix;

public class TestJama {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		double[][] array = {{0,0.5,0,0.5},{0.333,0,0,0.5},{0.333,0.5,0,0},{0.333,0,1,0}};
		double[][] V = {{0.25},{0.25},{0.25},{0.25}};
		

		Matrix t = new Matrix(array);
		Matrix v = new Matrix(V);
		print(t);
		print(v);
		Matrix tv = t.times(v);
		for(int i = 0 ; i< 10000; i ++){
			Matrix tmp = t.times(tv);
			if(equals(tv,tmp)){
				System.out.println("ok!");
				break;
			}
			tv = tmp;
		}
		print(tv);
	}
	
	public static boolean equals(Matrix a, Matrix b){

		Matrix c = a.minus(b);
		double squre = 0;
		for(int row = 0; row < c.getRowDimension(); row++ ){
			for(int col = 0; col < c.getColumnDimension(); col++){
				double x = c.get(row, col);
				squre +=(x*x);
			}
		}
		if(squre < 0.0001){
			return true;
		}else{
			return false;
		}
	}
	
	public static void print(Matrix c){
		for(int row = 0; row < c.getRowDimension(); row++ ){
			for(int col = 0; col < c.getColumnDimension(); col++){
				double x = c.get(row, col);
				System.out.print(x+" ");
			}
			System.out.println();
		}
	}

}
