package tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.MatrixUtils;
import Jama.Matrix;

public class TestJama {

	static Logger logger = LoggerFactory.getLogger(TestJama.class);

	public static void main(String[] args) {
		double[][] array = { { 1., 2., 3 }, { 4., 5., 6. }, { 7., 8., 10. } };
		Matrix A = new Matrix(array);
		// Matrix b = Matrix.random(3, 1);
		// Matrix x = A.solve(b);
		// Matrix Residual = A.times(x).minus(b);
		// double rnorm = Residual.normInf();
		// // System.out.println(rnorm);

		Matrix newA = MatrixUtils.lsa(A);
		Matrix newAA = MatrixUtils.calSVD(A);
		Matrix distance = A.minus(newA);

		MatrixUtils.printMatrixUtils(A);
		MatrixUtils.printMatrixUtils(newA);
		MatrixUtils.printMatrixUtils(newAA);
		MatrixUtils.printMatrixUtils(distance);
		System.out.println("********************************");
		double[][] arrayB = { { 1., 2., 3, 4 }, { 4., 5., 6., 7 },
				{ 7., 8., 10., 11 }, { 1, 2, 3, 3 }, { 2, 3, 2, 3 },
				{ 3, 3, 3, 3 } };
		Matrix B = new Matrix(arrayB);
		MatrixUtils.printMatrixUtils(B);
		double[][] arrayNew = { { 0, 0, 0, 4 }, { 0, 0, 0, 7 },
				{ 0, 0, 0, 11 }, { 1, 2, 3, 3 }, { 2, 3, 2, 3 }, { 3, 3, 3, 3 } };
		Matrix mNew = new Matrix(arrayNew);
		Matrix newB = MatrixUtils.customerPlus(mNew, newA);
		MatrixUtils.printMatrixUtils(newB);

		Matrix ob = MatrixUtils.lsa(B);
		Matrix nb = MatrixUtils.lsa(newB);
		Matrix nd = ob.minus(nb);
		System.out.println("********************************");

		MatrixUtils.printMatrixUtils(ob);
		MatrixUtils.printMatrixUtils(nb);
		MatrixUtils.printMatrixUtils(nd);

	}

}
