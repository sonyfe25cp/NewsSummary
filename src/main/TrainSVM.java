package main;

import java.io.IOException;

public class TrainSVM {

	public static void main(String[] args) {
		String[] trainArgs = {"train.data" };// directory of
															// training file
		String modelFile;
		try {
			modelFile = svm_train.main(trainArgs);
			String[] testArgs = { "test.data", modelFile,
					"test.data.result" };// directory of test file,
													// model
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// file, result file
		// Double accuracy = svm_predict.main(testArgs);
		// System.out.println("SVM Classification is done! The accuracy is "
		// + accuracy);
	}

}
