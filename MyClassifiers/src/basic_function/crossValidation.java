/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 *
 * @author User
 */
public class crossValidation {
    
    public static void crossValidation(Instances ins, Classifier tree, int fold) throws Exception{
        Evaluation eval = new Evaluation(ins);
        eval.crossValidateModel(tree, ins, fold, new Random(1));
        System.out.println(eval.toSummaryString());
    }
    
}
