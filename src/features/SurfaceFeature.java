package features;

import source.Sentence;

/**
 * Position
 * Doc_First
 * Para_First
 * Length
 * Quote
 * 
 * @author coder
 *
 */
public class SurfaceFeature implements FeatureCompute {

	public float[] compute(Sentence sentence){
		
		int positionId = sentence.getSentenceId(); 
		int total = sentence.getTotal();
		
		float position = (float)positionId / total;//feature 1
		int docFirst = 0;
		if(positionId ==1 ){
			docFirst = 1; //feature 2
		}
		int paraFirst = 0; //feature 3;
		
		int quote = 0; //feature 4;
		
		float[] surface ={position, docFirst, paraFirst, quote};
		
		return surface;
	}
	
}
