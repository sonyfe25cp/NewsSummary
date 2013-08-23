package service;

import java.util.ArrayList;
import java.util.List;

import source.Sentence;
import features.ContentFeature;
import features.EventFeature;
import features.FeatureCompute;
import features.RelevanceFeature;
import features.SurfaceFeature;

/**
 * @author coder
 *
 */
public class SentenceFeature {

	public double[] compute(Sentence sentence) {
		List<FeatureCompute> featureComputer = new ArrayList<FeatureCompute>();
		featureComputer.add(new ContentFeature());
		featureComputer.add(new EventFeature());
		featureComputer.add(new RelevanceFeature());
		featureComputer.add(new SurfaceFeature());
		
		double feature[] = null;
		if(sentence.getIsSummary().equals("true")){ //特征第一位是 label
			feature = new double[]{1};
		}else{
			feature = new double[]{0};
		}
		
		for(FeatureCompute compute : featureComputer){
			double[] featureTmp = compute.compute(sentence);
			feature = combine(feature, featureTmp);
		}
		return feature;
	}
	
	public String toString(Sentence sentence){
		double[] vector = compute(sentence);
		String line = "";
		for(int i = 0; i< vector.length; i ++){
			String tmp ="";
			if(i == 0){
				tmp = vector[0] +" ";
			}else{
				tmp = i + ":"+vector[i]+" ";
			}
			line += tmp;
		}
		return line;
	}
	
	private double[] combine(double[] array1, double[] array2){
		double[] com = new double[array1.length+array2.length];
		for(int i = 0 ; i < array1.length; i ++){
			com[i] = array1[i];
		}
		for(int i = 0 ; i < array2.length; i ++){
			com[i+array1.length] = array2[i];
		}
		return com;
	}
}
