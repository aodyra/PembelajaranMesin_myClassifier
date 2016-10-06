/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import weka.core.Instances;
import weka.classifiers.trees.J48;

/**
 *
 * @author User
 */
public class buildClassifier {
    
    public static void buildCLassifier (Instances ins) throws Exception{
        String[] options = new String[1];
        options[0] = "-U";            // unpruned tree
        J48 tree = new J48();         // new instance of tree
        tree.setOptions(options);     // set the options
        tree.buildClassifier(ins);   // build classifier
    }
    
}
