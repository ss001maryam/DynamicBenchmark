
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
import java.util.Scanner;

public class ConstraintGenerator {
    public static String fileName;    

 
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
    
    public static double[] readFileCsv(String fileName, int numChange){
 
        double [] vector = new double[numChange];
        int i=0;
        

        try {
            //Initialise IO capabilities
            Scanner scanner = new Scanner(new File(fileName));
             scanner.useDelimiter(",");
        while(scanner.hasNext()&&i<numChange){
           vector[i]= Double.valueOf(scanner.next());
            i++;
            
        }
        scanner.close();
            
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
        return vector;
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
    
    
    public static double [] generatedB(double b0, double a[][], int numChange, double lk, double uk){
        double [] b=new double[numChange];
                fileName = "dC_01";
                double random1,p=.3;

                b[0]=b0;         
                for (int i = 1; i < numChange; i++) {                

                        random1=Math.random();
                        
                        //Reflection method
                        if (random1<p) {
                         b[i] = b[i-1]+ (Math.random() * (uk-lk)+lk); //Math.random() * (uk -lk) + lk; 
                         p/=2;
                         }else{
                         b[i] = b[i-1]-(Math.random() * (uk-lk)+lk); //Math.random() * (uk -lk) + lk; 
                         p=1-(1-p)/2.0;
                        }
                          
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

        double b0=2;
        double lk=-5, uk=5; 
        int numChange = 100;
        int numVars = 30;
        //put numcasea to 2 for combinatorial changes
        int numCasea=1;
        double normVal = Math.sqrt((double)1/numVars);
       
        boolean exp3[] = new boolean[numChange];
        double alt = 0;
        for (int i = 0; i < numChange; i++) {
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
    
          
        double [][] a = new double[numChange][numVars];
        double sum = 0;
        
        for (int j = 0; j < numVars; j++) {
            if (numCasea == 1) {
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
            switch (numCasea) {
                case 1:
                    for (int j = 0; j < numVars; j++) {
                        a[i][j] = a[0][j];
                    }
                break;
                case 2://why 8?what if i want to reduce the dimention to 10, we put 3 for dimention 10
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
        double [] b=generatedB(b0, a, numChange, lk, uk);

           printFile(fileName, b,a, numChange, numVars,lk,uk);
            printCSV(fileName, b, numChange);
         

  
    }
}


  


