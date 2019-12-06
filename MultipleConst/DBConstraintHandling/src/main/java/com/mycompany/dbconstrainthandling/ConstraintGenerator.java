
package com.mycompany.dbconstrainthandling;


import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;//
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class ConstraintGenerator {
    public static String fileName;    
    public static int []randFreq;

    
    public static int[] freqPerm(int[] frequency, int numChange) {
        int []ret = new int[numChange];
        for (int i = 0; i < numChange; i++) {
            ret[i] = frequency[(int)(Math.random()*3)];
            // System.out.println(ret[i]);
        }
        return ret;
    }
    
    public static int[] freqGen(int frequency, int numChange){
       Random r=new Random ();
       
        int []ret = new int [numChange];
        for (int i = 0; i < numChange; i++) {
            int f = (int)(r.nextGaussian()*30+frequency);
            
            ret[i] = f;
        //    System.out.println(ret[i]);
        }
        /*
        //CALCULATE MEAN OF VALUES
        int mean = 0;
        for (int i = 0; i < numChange; i++) {
            mean = mean + ret[i];
        }
        mean = mean / numChange;
        System.out.println(mean);
        */
        return ret;
    }
    
public static void printFile(String name, double [][] matrix, int funciones, int ejecuciones ) {
        //String route = "/Users/Josue/Documents/MATLAB/Alarm_MOPSO" + it + ".txt";
        String route = name + ".csv";
        File f;
        f = new File(route);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < funciones; i++) {//por función 
                for(int j =0;j<ejecuciones; j++ ){
                    if(j!=ejecuciones-1){
                    pw.append(matrix[i][j] + ", ");
                    }
                    else{
                    pw.append(matrix[i][j] + ", ");                        
                    }
                }
                pw.append("\n");

            }
            
            pw.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ConstraintGenerator.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }    



public static void printFileFXs(String name, List<List<Double>> listFXs, int runs) {
        //String route = "/Users/Josue/Documents/MATLAB/Alarm_MOPSO" + it + ".txt";
        String route = name + ".csv";
        File f;
        int iterations;
        f = new File(route);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < runs; i++) {//por función 
                iterations=listFXs.get(i).size();
               // System.out.println("iterations "+iterations);
                for(int j =0;j<iterations ; j++ ){
                    if(j!=iterations-1){
                    pw.append(listFXs.get(i).get(j)+ ", ");
                    }
                    else{
                    pw.append(listFXs.get(i).get(j)+ ", ");                        
                    }
                }
                pw.append("\n");

            }
            
            pw.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ConstraintGenerator.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }    
   

  public static double[][] readFile(String fileName, int numChange, int numVars){
        
        
        String lineIter [] = new String[numChange];
        char lineDouble [] = new char[8];
        char lineDoubA [] = new char[5];
        fileName = fileName + ".txt";
        
        double [][] collection = new double[numChange][numVars+1];
        try {
            //Initialise IO capabilities
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //M:for skipping the first lines
            for (int m = 0; m < 5; m++) {
              bufferedReader.readLine();  
            }
    
            int i = -1;
                while(++i<numChange-1 && (lineIter[i] = bufferedReader.readLine()) != null ) {
                    char lineParse[] = lineIter[i].toCharArray();
                    
                    //Parse b values
                    for (int j = 6; j < 14; j++) {
                            lineDouble[j - 6] = lineParse[j];
                    }
                    
                    lineIter[i] = new String(lineDouble);
                    collection[i][0] = Double.parseDouble(lineIter[i]);
                    
                    //Parse all the a values using general formula
                    for (int j = 0; j < numVars; j++) {
                        for (int k = 17 + 6*j; k < 22 + 6*j; k++) {
                            lineDoubA[k - (17 + 6*j)] = lineParse[k];
                        }
                        lineIter[i] = new String(lineDoubA);
                        collection[i][j+1] = Double.parseDouble(lineIter[i]);
                    }
                }
            
            bufferedReader.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");  
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");   
        }
        //PRINT ALL VALUES INSIDE FUNCTION TEST
        /*
        for (int j = 0; j < numChange; j++) {
            for (int k = 0; k < numVars+1; k++) {
                System.out.print(collection[j][k]+",");
            }
            System.out.println("");
        }
        */
        return collection;
    }

    public static double[][] readFilex(String fileName, int numChange, int numVars){
        
        
        String lineIter [] = new String[numChange];
        char lineDouble [] = new char[8];
        char lineDoubA [] = new char[5];
        fileName = fileName + ".txt";
        
        double [][] collection = new double[numChange][numVars+1];
        try {
            //Initialise IO capabilities
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //M:for skipping the first lines
            for (int m = 0; m < 5; m++) {
              bufferedReader.readLine();  
            }
    
            int i = 0;
                while((lineIter[i] = bufferedReader.readLine()) != null) {
                    char lineParse[] = lineIter[i].toCharArray();
                    
                    //Parse b values
                    for (int j = 6; j < 14; j++) {
                            lineDouble[j - 6] = lineParse[j];
                    }
                    
                    lineIter[i] = new String(lineDouble);
                    collection[i][0] = Double.parseDouble(lineIter[i]);
                    
                    //Parse all the a values using general formula
                    for (int j = 0; j < numVars; j++) {
                        for (int k = 17 + 6*j; k < 22 + 6*j; k++) {
                            lineDoubA[k - (17 + 6*j)] = lineParse[k];
                        }
                        lineIter[i] = new String(lineDoubA);
                        collection[i][j+1] = Double.parseDouble(lineIter[i]);
                    }
                    
                    if (i < 199){
                    i++;
                    }
                    
                }
            
            bufferedReader.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");  
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");   
        }
        //PRINT ALL VALUES INSIDE FUNCTION TEST
        /*
        for (int j = 0; j < numChange; j++) {
            for (int k = 0; k < numVars+1; k++) {
                System.out.print(collection[j][k]+",");
            }
            System.out.println("");
        }
        */
        return collection;
    }
    
    public static void printCSV(String name, double [] b, int times) {
        String route = name + ".csv";
        File c;
        c = new File(route);
        try {
            FileWriter cw = new FileWriter(c);
            BufferedWriter bcw = new BufferedWriter(cw);
            PrintWriter cpw = new PrintWriter(bcw);
            DecimalFormat dec = new DecimalFormat("#000.000");
            for (int i = 0; i < times; i++) {
                cpw.append(dec.format(b[i]));
                if (i != times-1) {
                    cpw.append(",");
                }
            }
            cpw.append("\n");
            cpw.close();
            cw.close();
        } catch (IOException ex) {
            Logger.getLogger(ConstraintGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void printFile(String name, double [] b, double [][] a, int times, int vars, double lk,  double uk) {
        //String route = "/Users/Josue/Documents/MATLAB/Alarm_MOPSO" + it + ".txt";
        String route = name + ".txt";
        File f;
        f = new File(route);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.append("Problem name: "+name +"\n");
            pw.append("Number of times: "+times +"\n");
            pw.append("Lower value of k: "+lk +"\n");
            pw.append("Upper value of k: "+uk +"\n");
            pw.append("Times    b                                                                                  a \n");
            DecimalFormat dec = new DecimalFormat("#000.000");
            DecimalFormat deca = new DecimalFormat("#0.000");
            for (int i = 0; i < times; i++) {//por función 
                if (i < 9){
                    pw.append((i+1) + "     ");
                } else if (i > 8 && i < 99) {
                    pw.append((i+1) + "    ");                    
                } else if (i > 98) {
                    pw.append((i+1) + "   ");
                }
                if (b[i] >= 0) {
                    pw.append(" ");
                }
                pw.append(dec.format(b[i])+"   ");
                
                for (int j = 0; j < vars; j++) {
                    
                    pw.append(deca.format(a[i][j]));
                    if (j != vars-1) {
                        pw.append(",");
                    }
                }
                pw.append("\n");
                
            }
            pw.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ConstraintGenerator.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }    
   
     public static double[] getOpt(double[] w,double b){
        double result=0,opt=0;
        for (int i = 0; i < w.length; i++) {
            result+=w[i]*w[i];
        }result=Math.max(-2*b/result, 0);
        double[] x=new double[w.length];
        for (int i = 0; i < w.length; i++) {
            x[i]=(-w[i]*result/2);
        }return x;
    }
    public static double getOptFx(double[] w,double b){
        double[] x=getOpt(w,b);
        double sum=0;
        for (int i = 0; i < x.length; i++) {
            sum+=x[i]*x[i];
        }return sum;
    }   
    
    
    public static double [] generatedB(double b0, double a[][], int numChange, double lk, double uk, int numcase, double minpos, double maxpos, boolean exp3[]){
        double [] b=new double[numChange];
                fileName = "dC_0"+numcase;
                double lbound = 0;
                double ubound = 0;
                double low = 0;
                double high = 0;
        switch (numcase){
            case 1://random changes in size
                b[0]=b0;         
                for (int i = 1; i < numChange; i++) {                
                        lbound = Math.abs((b[i-1] - minpos) / minpos);//ABS not needed but keeps common formula between the two
                        ubound = Math.abs((b[i-1] - maxpos) / maxpos);
                        low = lk * lbound;
                        high = uk* ubound;
                        //Reflection method
                        b[i] = b[i-1]+ (Math.random() * (high - low) + low); //Math.random() * (uk -lk) + lk;     
                }
            break;

            
            case 2://multiple linear constraints
                b[0]=b0;
               for (int i = 1; i < numChange; i++) {
                    b[i] =b0;//b[i-1]+ Math.random() * (uk -lk) + lk;
                }
            break;
            
            case 3:
                b[0]=b0;
                for (int i = 1; i < numChange; i++) {                
                    if (exp3[i] == true) {    
                        lbound = Math.abs((b[i-1] - minpos) / minpos);//ABS not needed but keeps common formula between the two
                        ubound = Math.abs((b[i-1] - maxpos) / maxpos);
                        low = lk * lbound;
                        high = uk* ubound;
                        //Reflection method
                        b[i] = b[i-1]+ (Math.random() * (high - low) + low); //Math.random() * (uk -lk) + lk;  
                    } else {
                        b[i] = b[i-1];
                    }
                }
                
            break;
       
        }

        return b;
    }
    
    public static double[] swapEle(double[] arr, int a, int b){
        double temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        
        return arr;
    }
    
    public static void main(String[] args) {//b0=0, uk and lk=15

        double b0=0;
        double lk=-0.75, uk=0.75; //repeat for lk and uk=2, 4, 6, 10, 12, 15
        int numChange = 200;
        int numVars = 30;//reapeat for numVars=10, 30 and 50
        double minpos = -5, maxpos = 5;
        int numCase=1;
        
       
        double normVal = Math.sqrt((double)1/30);
       
        boolean exp3[] = new boolean[200];
        double alt = 0;
        for (int i = 0; i < 200; i++) {
            alt = Math.random();
            if (alt < 0.5) {
                exp3[i] = false;
            } else {
                exp3[i] = true;
            }
        }
          
          ArrayList numbers = new ArrayList();
          for(int k = 0; k < numVars; k++)
    {
      numbers.add(k);
     
    }
    Collections.shuffle(numbers);
    
          
        int []frequency = {200, 500, 1000};//25,50,100
        double [][] a = new double[numChange][numVars];
        double sum = 0;
        for (int j = 0; j < numVars; j++) {
            if (numCase == 1) {
                a[0][j] = normVal;
                sum+= Math.pow(a[0][j],2);
            } else {
                a[0][j] =  Math.random();
                sum+= Math.pow(a[0][j],2);
            }
        }
        sum = Math.sqrt(sum);
        for (int j = 0; j < numVars; j++) {
            a[0][j] = a[0][j]/sum;
        }
        
        //TEST NORM
        sum = 0;
        for (int j = 0; j < numVars; j++) {
            sum+= Math.pow(a[0][j],2);
        }
        sum = Math.sqrt(sum);
        //
        for (int i = 1; i < numChange; i++) {
            Collections.shuffle(numbers);
           // System.out.println("numbers "+ numbers);
    
            for (int j = 0; j < numVars; j++) {
                a[i][j] =a[i-1][j];
            }
            switch (numCase) {
                case 1:
                    for (int j = 0; j < numVars; j++) {
                        a[i][j] = a[0][j];
                    }
                break;
                case 2:
                    for (int k = 0; k < 8; k++){
                        a[i] = swapEle(a[i], (int)numbers.get(k),(int)numbers.get(29-k));
                    }
                    //Normalise here
                    sum = 0;
                    for (int k = 0; k < numVars; k++) {
                        sum+= Math.pow(a[i][k],2);
                    }
                    sum = Math.sqrt(sum);
                    for (int k = 0; k < numVars; k++) {
                        a[i][k] = a[i][k]/sum;
                    }
                break;
                case 3:
                    if (exp3[i] == false){
                        for (int k = 0; k < 8; k++){
                            a[i] = swapEle(a[i], (int)numbers.get(k),(int)numbers.get(29-k));
                        }
                    }
                    //Normalise here
                    sum = 0;
                    for (int k = 0; k < numVars; k++) {
                        sum+= Math.pow(a[i][k],2);
                    }
                    sum = Math.sqrt(sum);
                    for (int k = 0; k < numVars; k++) {
                        a[i][k] = a[i][k]/sum;
                    }
                break;
            }
            
        }
        //Run for Cases
       
            //Generate b vector
            double [] b=generatedB(b0, a, numChange, lk, uk, numCase, minpos, maxpos, exp3);
            //double [] a=new double[numChange];
            /*for (int i = 0; i < numChange; i++) {
                System.out.print(", "+b[i]);
                if(i%10==0 && i!=0)
                    System.out.println("");
            }*/
            
            printFile(fileName, b, a, numChange, numVars, lk, uk);
            printCSV(fileName, b, numChange);
          //  double[][]ab = readFile(fileName, numChange, numVars);
            //PRINT WHOLE FORMATTED 2D MATRIX [B IN COL 0, A IN COL 1-31]
            
           
            
        
        
        //GENERATE BIAS-RANDOM FREQ SET
        int selection = 1; 
        switch (selection) {
            case 1: 
                 randFreq = freqGen(frequency[1], numChange);  
                break;
   
            case 2:
               randFreq = freqPerm(frequency, numChange);
                break;
        }
       
        
    }
}


  


