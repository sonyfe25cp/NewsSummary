package service;

import java.util.ArrayList;
import java.util.List;

import model.Sentence;

import features.ContentFeature;
import features.EventFeature;
import features.FeatureCompute;
import features.RelevanceFeature;
import features.SurfaceFeature;

/**
 *
 * @author Chen Jie
 * @date 15 Mar, 2014
 */
public class SentenceFeature {

    private SentenceService sentenceService;

    public double[] compute(Sentence sentence) {
        List<FeatureCompute> featureComputer = new ArrayList<FeatureCompute>();
        featureComputer.add(new ContentFeature());
        featureComputer.add(new EventFeature());
        RelevanceFeature rf = new RelevanceFeature();
        rf.setSentenceService(sentenceService);
        featureComputer.add(rf);
        featureComputer.add(new SurfaceFeature());

        double feature[] = null;
        if (sentence.getIsSummary().equals("true")) { //特征第一位是 label
            feature = new double[] { 1 };
        } else {
            feature = new double[] { 0 };
        }

        for (FeatureCompute compute : featureComputer) {
            double[] featureTmp = compute.compute(sentence);
            feature = combine(feature, featureTmp);
        }
        return feature;
    }

    public String toString(Sentence sentence) {
        double[] vector = compute(sentence);
        String line = "";
        for (int i = 0; i < vector.length; i++) {
            String tmp = "";
            if (i == 0) {
                tmp = vector[0] + " ";
            } else {
                tmp = i + ":" + vector[i] + " ";
            }
            line += tmp;
        }
        return line;
    }

    private double[] combine(double[] array1, double[] array2) {
        double[] com = new double[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            com[i] = array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            com[i + array1.length] = array2[i];
        }
        return com;
    }

    public SentenceService getSentenceService() {
        return sentenceService;
    }

    public void setSentenceService(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }
}
