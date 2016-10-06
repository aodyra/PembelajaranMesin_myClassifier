/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclassifiers;

import MyID3.MyID3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 *
 * @author aodyra
 */
public class MyClassifiers {
    
    public static MyID3 myID3 = new MyID3();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        
        BufferedReader reader = new BufferedReader(new FileReader("data/weather.nominal.arff"));
        Instances data = new Instances(reader);
        reader.close();
        data.setClassIndex(4);
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(myID3, data, 10, new Random(1));
        System.out.println(eval.toSummaryString());
        
    }
    
}
