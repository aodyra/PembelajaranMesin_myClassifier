/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

/**
 *
 * @author User
 */
public class testSet {
    
    public static void testSet(Instances n_train, Instances n_test, Classifier n_cls) throws Exception{
        Instances train = n_train;   // from somewhere
        Instances test = n_test;    // from somewhere
        // train classifier
        Classifier cls = n_cls;
        cls.buildClassifier(train);
        // evaluate classifier and print some statistics
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(cls, test);
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }
    
}
