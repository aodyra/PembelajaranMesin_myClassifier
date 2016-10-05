/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import weka.classifiers.Classifier;
import weka.core.Instance;

/**
 *
 * @author User
 */
public class classifyUnseenData {
    
    public static double classifyUnseen(Classifier cl,Instance inst) throws Exception{
        return cl.classifyInstance(inst);
    }
    
}
