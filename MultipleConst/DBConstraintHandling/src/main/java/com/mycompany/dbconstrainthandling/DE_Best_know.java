


package com.mycompany.dbconstrainthandling;

import static com.mycompany.dbconstrainthandling.ConstraintGenerator.readFile;
import static com.mycompany.dbconstrainthandling.ConstraintGenerator.printFileFXs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author maryam
 */

public class DE_Best_know {

    private static void getmultOpt(double[] d, double d0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String getOptmultiFx(double[] d, double d0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Individual> population;
    public double CR;
    public int NP;
    public int numF;
    public int D;
    public double[] lowerLimit;
    public double[] upperLimit;
    public double betamin = .20;
    public double betamax = .80;
    public double t = 0;
    public List<Double> SumCVs ;
    public List<Double> Fs ;
    public double b;
    public double b1;


    public DE_Best_know(int NP, int D, int numF, double CR, double [][][]vvv, int numCons, double t) {
        this.NP = NP;
        this.numF = numF;
        this.D = D;
        this.CR = CR;
        
        Fs = new ArrayList();
        SumCVs = new ArrayList();
        lowerLimit = new double[this.D];
        upperLimit = new double[this.D];
        for (int i = 0; i < D; i++) {
            lowerLimit[i] = -5;
            upperLimit[i] = 5;
        }
        population = new ArrayList();
        for (int i = 0; i < this.NP; i++) {
            population.add(new Individual(D));
            for (int j = 0; j < this.D; j++) {
                population.get(i).x[j] = Math.random() * (upperLimit[j] - lowerLimit[j]) + lowerLimit[j];

            }
            population.get(i).getFitness(this.numF, t, vvv,numCons, false);
        }

    }
    

    public Individual getBest(Individual vecU, Individual ind2) {
        //both Individuals are feasible 
        if (vecU.SumR == 0 && ind2.SumR == 0) {
            if (vecU.fx <= ind2.fx) {
                return vecU;
            } else {
                return ind2;
            }
        }
        //If one of them is feasible
        if (vecU.SumR == 0 && ind2.SumR > 0) {
            return vecU;
        }
        if (vecU.SumR > 0 && ind2.SumR == 0) {
            return ind2;
        }
        //both of them are infeasible  
        if (vecU.SumR <= ind2.SumR) {
            return vecU;
        }
        return ind2;

    }

    public void CopyIndividual(Individual ind, int i) {
        for (int j = 0; j < ind.x.length; j++) {
            population.get(i).x[j] = ind.x[j];
        }
        population.get(i).fx = ind.fx;
        population.get(i).SumR = ind.SumR;
        population.get(i).g = new ArrayList();
        for (int m = 0; m < ind.g.size(); m++) {
            population.get(i).g.add(ind.g.get(m));
        }
    }
public static void printFileFXsNew(String name, List<Double> listFXs, int times) {
        //String route = "/Users/Josue/Documents/MATLAB/Alarm_MOPSO" + it + ".txt";
        String route = name + ".csv";
        File f;
        int iterations;
        f = new File(route);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 0; i < times; i++) {//por función 
                    pw.append(listFXs.get(i)+ ", ");                        

            }
            
            pw.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(DE_Best_know.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
//            Fs.add(bestOne.fx);//put fixed 
    //        SumCVs.add(bestOne.SumR);//put fixed
    public static void main(String[] args) throws IOException {
      String[] Sfuncion = {"NumFunc_1", "NumFunc_2","NumFunc_3", "NumFunc_4"};
        DecimalFormat df = new DecimalFormat("0.000");

        //variables
        int startFunc = 1, MAX_Functions = 4, runs = 1;// variables for the experiments 
        int NP = 20, D = 30, eval, numVec = 3, gen, numCons=5;
        double TIMEs = 100, t = 0, CR = 0.3, F, betamin = .2, betamax = .8;
        int MAX_EVAL = 1000000;
     
        List<Double> ListFXs;
        List<Double> ListSumCV;
        List<List<Double>> ListSolutions;
        List<List<Double>> Solutions;
        
        int numChange = 100;
        int  MAX_t=numChange-1;
        
        String name = "Best_Know";   

        System.out.println("Get_Best_know_per_time ");
        for (int numF = startFunc; numF <= MAX_Functions; numF++) {
            
            ListFXs = new ArrayList<Double>();
            ListSumCV = new ArrayList<Double>();//M:added for keeping SumCV
            ListSolutions = new ArrayList<List<Double>>();//M:added for keeping list of f(objective function) after every time change

            
                          
              //String fileName = "dC_01";  
              //double vv[][] = readFile(fileName, numChange, numCons);
              double vvv [][][]=new double [numCons][numChange][D+1];
            for (int i = 0; i < numCons; i++) {
                vvv[i]=readFile("dC_0"+i, numChange, D);
            }
            
            
         for (int i = 0; i < numChange; i++) {
                if (i==0) {
                    for (int j = 0; j < numCons; j++) {
                        for (int k = 0; k < D+1; k++) {
                            vvv[j][0][k]=vvv[j][0][k];
                        }
                    }
                    } else{
                    double mod1=i%5;
                    for (int j = 0; j < numCons; j++) {
                        if (j==mod1) { 
                            for (int k = 0; k < D+1; k++) {
                                vvv[j][i][k]=vvv[j][i][k];
                            }                            

                            }else{
                            
                            for (int k = 0; k < D+1; k++) {
                                 vvv[j][i][k]=vvv[j][i-1][k];
                            }
                           

                        }

                        }
                    }

                }
         
            
            System.out.println("\n Function " + Sfuncion[numF - 1]);
            for (int time = 0; time < (int) TIMEs; time++) {
                t = (double) time;
                /*
                     for (int i = 0; i < numCons; i++) {
                System.out.println(vv[i][(int) t]);
            }
*/
                    List<Double> FXs = new ArrayList();
                    List<Double> SumCV = new ArrayList();
                    List<Double> sol = new ArrayList();
                    Solutions = new ArrayList<List<Double>>();//M:added for keeping list of f(objective function) after every time change
                System.out.println("\n time " + t);
                for (int r = 0; r < runs; r++) {
                    gen = 0;
                    eval = 0;
  
                    DE_Best_know DE = new DE_Best_know(NP, D, numF, CR,  vvv,numCons, (double)t);// A random population was created
                    Individual bestOne = new Individual(DE.D);
                    bestOne = DE.population.get(0).clone();
                    for (int i = 1; i < DE.NP; i++) {
                        bestOne = DE.getBest(bestOne, DE.population.get(i)).clone();
                    }
                    while (eval < MAX_EVAL) {
                        gen++;
                        for (int i = 0; i < DE.NP; i++) {

                            //start to evolve the population
                            //chose three differents vectors 
                            Individual ind = new Individual(DE.D);
                            List<Individual> vectors = new ArrayList();
                            Individual vecU = new Individual(DE.D);
                            int indp;
                            for (int v = 0; v < numVec; v++) {
                                indp = i;
                                while (indp == i) {
                                    indp = (int) (Math.random() * (double) NP);
                                    if (vectors.contains(DE.population.get(i))) {
                                        indp = i;
                                    }
                                }
                                vectors.add(DE.population.get(indp).clone());
                            }//end for choose three random vectors 
                            int jrand = (int) (Math.random() * DE.D);
                            for (int j = 0; j < DE.D; j++) {
                                F = Math.random() * (betamax - betamin) + betamin;

                                if (Math.random() < CR || j == jrand) {
                                    ind.x[j] = vectors.get(0).x[j] + F * (vectors.get(1).x[j] - vectors.get(2).x[j]);
                                    ind.x[j] = Math.max(ind.x[j], DE.lowerLimit[j]);
                                    ind.x[j] = Math.min(ind.x[j], DE.upperLimit[j]);
                                } else {
                                    ind.x[j] = DE.population.get(i).x[j];
                                }

                            }
                            ind.getFitness(numF, t,  vvv, numCons, false);
                            ind = DE.getBest(ind, DE.population.get(i)).clone();
                            DE.CopyIndividual(ind, i);//Replace the information of the ith population for the new individual; 
                            //Verified how if the new individual is the best  
                            bestOne = DE.getBest(bestOne, DE.population.get(i)).clone();

                            eval++;
                            }//end evolve population
                  }// end of MAX_Evaluations (runs)
                        
                    for (int s = 0; s < bestOne.x.length; s++) {
                            sol.add(bestOne.x[s]);
                        }
                     Solutions.add(sol);
                     FXs.add(bestOne.fx);
                     SumCV.add(bestOne.SumR);
                     
                }//end of runs
                //after the 30 runs we choose the best of the runs 
                int ind=0;
                for (int r = 1; r < runs; r++) {
                    //both are feasible 
                    if(SumCV.get(ind)==0 && SumCV.get(r)==0){
                        if(FXs.get(ind)>FXs.get(r))
                            ind=r;
                    }
                    else{
                        if(SumCV.get(ind)>SumCV.get(r))
                            ind=r;
                    }
                }
                //System.out.println(" FXs "+FXs);                
        ListFXs.add(FXs.get(ind));
        ListSumCV.add(SumCV.get(ind));
        ListSolutions.add(Solutions.get(ind));
                
            }//end of times 
            System.out.println("ListFXs.size()  "+ListFXs.size());
            printFileFXsNew((name + numF + "Fxs"), ListFXs,ListFXs.size());
            printFileFXsNew((name + numF + "SumCV"), ListSumCV, ListFXs.size());
            printFileFXs((name + numF + "Solutions"), ListSolutions, D);//we need the dimension of the problem


        }//end of the functions

  
}
}//end class




