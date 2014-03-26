//
//
//import model.Sentence;
//import service.SentenceFeature;
//import service.SentenceService;
//
//public class TestComputeFeature {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		SentenceService ss = new SentenceService();
//		Sentence s = ss.getFirstSenceOfDoc("APW19990422.0082");
//		SentenceFeature sf = new SentenceFeature();
//		double[] f = sf.compute(s);
//		for(double d : f){
//			System.out.print(d+" ");
//		}
//		
//	}
//
//}
