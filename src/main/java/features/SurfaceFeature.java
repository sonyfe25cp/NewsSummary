//package features;
//
//import model.Sentence;
//
///**
// * Position Doc_First Para_First Length Quote
// * 
// * @author coder
// * 
// */
//public class SurfaceFeature implements FeatureCompute<Sentence> {
//
//    public double[] compute(Sentence sentence) {
//        int positionId = sentence.getSentenceId();
//        int total = sentence.getTotal();
//
//        float position = (float) positionId / total;//feature 1
//
//        int docFirst = 0;
//        if (positionId == 1) {
//            docFirst = 1; //feature 2
//        }
//        int paraFirst = 0; //feature 3;
//
//        int length = sentence.getSentenceContent().length(); //feature 4
//
//        int quote = 0; //feature 5;
//
//        double[] surface = { position, docFirst, paraFirst, length, quote };
//
//        return surface;
//    }
//
//}
