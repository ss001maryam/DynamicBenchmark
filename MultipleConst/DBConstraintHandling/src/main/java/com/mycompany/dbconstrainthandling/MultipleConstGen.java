
package com.mycompany.dbconstrainthandling;
import Jama.Matrix;
import static com.mycompany.dbconstrainthandling.ConstraintGenerator.getOpt;
import static com.mycompany.dbconstrainthandling.ConstraintGenerator.getOptFx;
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MultipleConstGen {
    public static String fileName;
    public static double[][][] a;
    public static double[][] b;
   

    
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
      

public static void printFileFXs(String  name, List<List<Double>> listFXs, int runs) {
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
  
    
     public static double[] getmultOpt(double[][] w,double[] b){
         Matrix W=new Matrix(w),B=(new Matrix(new double[][]{b})).transpose();
         Matrix lambda=W.times(W.transpose()).inverse().times(B).times(-2.0);
         for (int i = 0; i < lambda.getColumnDimension(); i++) {
             lambda.set(i,0,Math.max(0,lambda.get(i,0)));
         }Matrix X=W.transpose().times(lambda).times(-1.0/2.0);
         return X.transpose().getArray()[0];
     }
    public static double getOptmultiFx(double[][] w,double[] b){
        //a is now w 
        double[] x=getmultOpt(w,b);
        double sum=0;
        for (int i = 0; i < x.length; i++) {
            sum+=x[i]*x[i];
        }return sum;
    }   
    
    
    public static double [] generatedB(double b0, double a[][], int numChange, double lk, double uk, int numcase, double minpos, double maxpos, boolean exp3[]){
        double [] b=new double[numChange];

                
                double lbound = 0;
                double ubound = 0;
                double low = 0;
                double high = 0;
                
                double random1,p=.3;
        switch (numcase){
            case 1://random changes in size
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
    
    public static void Generate(String fileName, int numCase, double [] vec, int numChange, int numVars ) {

        double b0=vec[0],lk=vec[1], uk=vec[2], minpos=vec[3], maxpos=vec[4] ;

        
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

            printFile(fileName, b, a, numChange, numVars, lk, uk);
            printCSV(fileName, b, numChange);
          //  double[][]ab = readFile(fileName, numChange, numVars);
            //PRINT WHOLE FORMATTED 2D MATRIX [B IN COL 0, A IN COL 1-31]
            
           
            
  
        
    }
    
public static void main(String[] args){
   int numCase=3;
   int numCons=5;
   int numChange = 200;
   int numVars = 30;
   
        double b0=0;
        double lk=-0.75, uk=0.75; 

        double minpos = -5, maxpos = 5;
        double [] vec={b0,lk,uk,minpos,maxpos};
      /*
           for (int i = 0; i < numChange; i++) {
            double[] x=getmultOpt(a[i], b[i]);
            String s="";
            for (int j = 0; j < x.length; j++) {
                s+=" "+x[j];
            }
            System.out.println(getmultOpt(a[i], b[i])+" opt "+s);
        }
*/
   String [] File=new String[numCons];
   
    for (int r = 0; r < numCons; r++) {
        File[r] = "dC_0"+r;
        fileName=File[r];
        Generate(fileName, numCase, vec, numChange, numVars);
    }

}
}




  



