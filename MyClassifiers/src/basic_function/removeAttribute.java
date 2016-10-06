/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import java.io.BufferedReader;
import java.io.FileReader;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author User
 */
public class removeAttribute {
    
    public static Instances removeAtt (Instances att)throws Exception{
        Instances output = null;
//        Instances instNew;
        Remove remove = new Remove();
 
        if (att.classIndex() < 0){
            output = att;
        } else {
            remove.setAttributeIndices("" + (att.classIndex() + 1));
            remove.setInvertSelection(false);
            remove.setInputFormat(att);
            output = Filter.useFilter(att, remove);
    //        System.out.println(instNew);
        }
        
        return output;
    }
}
