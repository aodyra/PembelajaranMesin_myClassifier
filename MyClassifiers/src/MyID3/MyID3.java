/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyID3;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.NoSupportForMissingValuesException;
import weka.core.Utils;

/**
 *
 * @author aodyra
 */
public class MyID3 extends Classifier{
    
    private Attribute nodeAttribute;
    
    private MyID3[] descendant;
    
    private double classValue;
    private Attribute classAttribute;

    @Override
    public void buildClassifier(Instances i) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        i = new Instances(i);
        i.deleteWithMissingClass();
        makeTree(i);
    }
    
    private void makeTree(Instances data) throws Exception{
        if (data.numInstances() == 0) {
            nodeAttribute = null;
            classValue = Instance.missingValue();
            return;
        }
        double[] infoGains = new double[data.numAttributes()];
        for(int i = 0; i < infoGains.length-1; ++i){
            infoGains[i] = computeInfoGain(data, data.attribute(i));
//            System.out.println(infoGains[i]);
        }
        nodeAttribute = data.attribute(Utils.maxIndex(infoGains));
        
        if(Utils.eq(infoGains[nodeAttribute.index()], 0)){
            nodeAttribute = null;
            double[] countClass = new double[data.numClasses()];
            for(int i = 0; i < data.numInstances(); ++i){
                countClass[(int) data.instance(i).classValue()]++;
            }
            
            classValue = Utils.maxIndex(countClass);
            classAttribute = data.classAttribute();
        } else {
            Instances[] splitData = splitData(data, nodeAttribute);
            descendant = new MyID3[nodeAttribute.numValues()];
            for(int i = 0; i < descendant.length; ++i){
                descendant[i] = new MyID3();
                descendant[i].makeTree(splitData[i]);
            }
        }
    }
    
    public double classifyInstance(Instance instance) throws NoSupportForMissingValuesException{
        if(instance.hasMissingValue())
            throw new NoSupportForMissingValuesException("Id3: cannot process instance with missing value");
        double result = 0;
        if(nodeAttribute == null){
            return classValue;
        } else {
            return descendant[(int) instance.value(nodeAttribute)].classifyInstance(instance);
        }
    }
    
    private Instances[] splitData(Instances data, Attribute attr) throws Exception{
        Instances[] result = new Instances[attr.numValues()];
        for(int i = 0; i < result.length; ++i){
            result[i] = new Instances(data, data.numInstances());
        }   
        for(int i = 0; i < data.numInstances(); ++i){
            Instance current = data.instance(i);
            result[(int) current.value(attr)].add(current);
        }
        for(int i = 0; i < result.length; ++i){
            result[i].compactify();
        }
        return result;
    }
    
    private double computeInfoGain(Instances data, Attribute attr) throws Exception{
        Instances[] splitData = splitData(data, attr);
        double totalInstance = data.numInstances();
        double result = computeEntropy(data);
        for(int i = 0; i < splitData.length; ++i){
            if(splitData[i].numInstances() > 0)
                result -= ((double)splitData[i].numInstances()/totalInstance) * 
                        computeEntropy(splitData[i]);
        }
        return result;
    }
    
    private double computeEntropy(Instances data) throws Exception{
        int distinctClass = data.numClasses();
        double totalInstances = data.numInstances();
        double[] countClass = new double[distinctClass];
        for(int i = 0; i < totalInstances; ++i){
            countClass[(int) data.instance(i).classValue()]++;
        }
        double result = 0;
        for(int i = 0; i < distinctClass; ++i){
            if(countClass[i] > 0){
                result -= (countClass[i]/totalInstances) * Utils.log2(countClass[i]/totalInstances);
            }
        }
//        System.out.println("jancuk " + result);
        return result;
    }
    
    public String toString() {

        if (descendant == null) {
          return "Id3: No model built yet.";
        }
        return "Id3\n\n" + toString(0);
    }
    
    private String toString(int level) {
        StringBuffer text = new StringBuffer();

        if (nodeAttribute == null) {
            if (Instance.isMissingValue(classValue)) {
            text.append(": null");
          } else {
            text.append(": " + classAttribute.value((int) classValue));
          } 
        } else {
          for (int j = 0; j < nodeAttribute.numValues(); j++) {
            text.append("\n");
            for (int i = 0; i < level; i++) {
              text.append("|  ");
            }
            text.append(nodeAttribute.name() + " = " + nodeAttribute.value(j));
            text.append(descendant[j].toString(level + 1));
          }
        }
        return text.toString();
      }
}
