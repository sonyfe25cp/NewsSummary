package utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

/**
 * 跟矩阵有关的运算
 * 
 * @author Chen Jie
 * @date 17 Mar, 2014
 */
public class MatrixUtils {

	static Logger logger = LoggerFactory.getLogger(MatrixUtils.class);

	public static void printMatrixUtils(Matrix matrix) {
		System.out.println("----------------------------------------------");
		int row = matrix.getRowDimension();
		int col = matrix.getColumnDimension();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix.get(i, j) + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 不同大小的矩阵也可以相加
	 * 
	 * @return
	 */
	public static Matrix customerPlus(Matrix... array) {
		int maxRow = 0;
		int maxCol = 0;
		for (Matrix m : array) {
			maxRow = maxRow >= m.getRowDimension() ? maxRow : m
					.getRowDimension();
			maxCol = maxCol >= m.getColumnDimension() ? maxCol : m
					.getColumnDimension();
		}
		List<Matrix> newArray = new ArrayList<Matrix>();
		for (Matrix m : array) {
			if (m.getRowDimension() != maxRow
					|| m.getColumnDimension() != maxCol) {
				Matrix newM = new Matrix(maxRow, maxCol);
				newM.setMatrix(0, m.getRowDimension() - 1, 0,
						m.getColumnDimension() - 1, m);
				newArray.add(newM);
			} else {
				newArray.add(m);
			}
		}
		Matrix tmp = new Matrix(maxRow, maxCol);
		for (Matrix m : newArray) {
			tmp.plusEquals(m);
		}
		return tmp;
	}

	/**
	 * 简单lsa操作
	 * 
	 * @param matrix
	 * @param top
	 * @return
	 */
	public static Matrix lsa(Matrix matrix) {
		SingularValueDecomposition svd = new SingularValueDecomposition(matrix);
		Matrix u = svd.getU();
		Matrix v = svd.getV();
		Matrix s = svd.getS();
		
		int top = svd.rank() * 8 / 10;
//		logger.info("svd 结果u: row = {}, col = {}",u.getRowDimension(), u.getColumnDimension());
//		logger.info("svd 结果s: row = {}, col = {}",s.getRowDimension(), s.getColumnDimension());
//		logger.info("svd 结果v: row = {}, col = {}",v.getRowDimension(), v.getColumnDimension());

		Matrix nU = new Matrix(u.getRowDimension(), top);
		nU.setMatrix(0, u.getRowDimension() - 1, 0, top - 1, u);

		Matrix nV = new Matrix(v.getRowDimension(), top);
		nV.setMatrix(0, v.getRowDimension() - 1, 0, top - 1, v);

		Matrix nS = new Matrix(top, top);
		nS.setMatrix(0, top - 1, 0, top - 1, s);

		Matrix newMatrix = nU.times(nS).times(nV.transpose());
		return newMatrix;
	}

	public static Matrix calSVD(Matrix matrix) {
		SingularValueDecomposition svd = new SingularValueDecomposition(matrix);
		int d = svd.rank() * 8 / 10;
		Matrix u = svd.getU().getMatrix(0, matrix.getRowDimension() - 1, 0, d);// 左矩阵
		Matrix s = svd.getS().getMatrix(0, d, 0, d);// 中间矩阵
		Matrix v = svd.getV().getMatrix(0, matrix.getColumnDimension() - 1, 0,
				d);// 右矩阵
		
		logger.info("svd 结果u: row = {}, col = {}",u.getRowDimension(), u.getColumnDimension());
		logger.info("svd 结果s: row = {}, col = {}",s.getRowDimension(), s.getColumnDimension());
		logger.info("svd 结果v: row = {}, col = {}",v.getRowDimension(), v.getColumnDimension());
		
		Matrix matrix_new = u.times(s).times(v.transpose());
		return matrix_new;
	}

	public static void addValueToRow(Matrix matrix, double value, int rowNum) {//给指定行非0项增加value
		int row = matrix.getRowDimension();
		int cols = matrix.getColumnDimension();
		if(rowNum > row){
			logger.error("给行增加数值错误，指定行数大于矩阵行数");
			System.exit(0);
		}
		for(int i = 0; i < cols; i++){
			double oldValue = matrix.get(rowNum, i);
			if(oldValue == 0){
				continue;
			}else{
				double newValue = oldValue + value;
				matrix.set(rowNum, i, newValue);
			}
		}
	}

	public static double computeColsSum(Matrix matrix, int i) {
		if(i > matrix.getColumnDimension()){
			logger.error("要计算的列数大于矩阵列数");
			System.exit(0);
		}
		double res = 0;
		for(int j = 0 ; j< matrix.getRowDimension(); j++){
			double d = matrix.get(j, i);
			if(d > 0){
				res += d;
			}
		}
		return res;
	}
	
	// /**
	// * 约定一个feature一行，列是featureItem
	// *
	// * @param features
	// * @return
	// */
	// public static Matrix merge(Feature... features) {
	// if (features == null || features.length == 0) {
	// return null;
	// }
	// int rows = features.length;
	// int cols = features[0].getFeature().length;
	// Matrix matrix = new Matrix(rows, cols);
	// int rowIndex = 0;
	// for (Feature feature : features) {
	// if (feature.getFeature().length != cols) {
	// logger.error("特征长度不统一, 程序退出");
	// System.exit(0);
	// }
	// double[] f = feature.getFeature();
	// for (int i = 0; i < f.length; i++) {
	// matrix.set(rowIndex, i, f[i]);
	// }
	// rowIndex++;
	// }
	// return matrix;
	// }
}
