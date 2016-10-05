/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author User
 */
public class loadData {
    
    public static Instances getData (String target) throws Exception {
       DataSource source = new DataSource(target);
       Instances data = source.getDataSet();
        // setting class attribute if the data format does not provide this information
        // For example, the XRFF format saves the class attribute information as well
        if (data.classIndex() == -1){
           data.setClassIndex(data.numAttributes() - 1); 
        }
        return data;
    }
}
