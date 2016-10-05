/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.classifiers.Classifier;

/**
 *
 * @author User
 */
public class percentageSplit {
    
    public static void percentageSplit(Instances trainingSet, Classifier cls) throws Exception{
        int trainSize = (int) Math.round(trainingSet.numInstances() * 0.8);
        int testSize = trainingSet.numInstances() - trainSize;
        Instances train = new Instances(trainingSet, 0, trainSize);
        Instances test = new Instances(trainingSet, trainSize, testSize);
        
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(cls, test);
    }
    
    public static double percentageSplitRate (Instances trainingSet, Classifier cls) throws Exception{
        int trainSize = (int) Math.round(trainingSet.numInstances() * 0.8);
        int testSize = trainingSet.numInstances() - trainSize;      
        Instances train = new Instances(trainingSet, 0, trainSize);
        Instances test = new Instances(trainingSet, trainSize, testSize);
        
        Evaluation eval;
        eval = new Evaluation(train);
        eval.evaluateModel(cls, test);
        return eval.pctCorrect();
    }
    
}
