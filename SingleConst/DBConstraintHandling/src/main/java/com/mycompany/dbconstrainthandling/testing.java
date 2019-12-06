/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbconstrainthandling;
import static com.mycompany.dbconstrainthandling.ConstraintGenerator.readFileCsv;

/**
 *
 * @author yaneliamecaalducin
 */
public class testing {
    public static void main(String[] args) {
        //Best_Know1FsLargeb.csv
        double [] vec =readFileCsv("Best_Know1FxsLarge.csv", 200);
        System.out.println("vec "+vec.length);
    }
}
