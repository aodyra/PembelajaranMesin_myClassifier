/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic_function;

import weka.core.Instances;
import weka.filters.supervised.instance.Resample;
import weka.filters.Filter;

/**
 *
 * @author User
 */
public class filterResample {
    
    public static Instances filterResample(Instances ins) {
        
        final Resample filter = new Resample();
        Instances filteredIns = null;
        filter.setBiasToUniformClass(1.0);
        try {
            filter.setInputFormat(ins);
            filter.setNoReplacement(false);
            filter.setSampleSizePercent(100);
            filteredIns = Filter.useFilter(ins, filter);
	} catch (Exception e) {
            System.out.println("Error when resampling input data!");
            e.printStackTrace();
	}
	return filteredIns;
    }
}
