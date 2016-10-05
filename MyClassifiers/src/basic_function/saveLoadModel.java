/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 *
 * @author User
 */
public class saveLoadModel {
    
    public static void saveModel (String source, String target) throws IOException, Exception{
        // create J48
        Classifier cls = new J48();

        // train
        Instances inst = new Instances(
                           new BufferedReader(
                             new FileReader(source)));
        inst.setClassIndex(inst.numAttributes() - 1);
        cls.buildClassifier(inst);

        // serialize model
        weka.core.SerializationHelper.write(target, cls);
    }
    
    public static Classifier loadModel (String source) throws Exception{
        // deserialize model
        Classifier cls = (Classifier) weka.core.SerializationHelper.read(source);
        return cls;
    }
}
