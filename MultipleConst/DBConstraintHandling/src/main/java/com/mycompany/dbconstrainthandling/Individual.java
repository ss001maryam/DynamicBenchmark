package com.mycompany.dbconstrainthandling;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaneliamecaalducin
 */
public class Individual implements Comparable<Individual> {

    public double[] x;
    public int numVar;
    public double fx = 0;
    public double fnor = 0;//for penalty in order to normalize the fx 
    public List<Double> g;//inequality 
    public List<Double> h;//equality 
    public double SumR = 0;
   

   
    double nom = 0;
    double denom = 0;

//  public double error;
    public Individual(int n) {
        this.numVar = n;
        x = new double[n];
    }

    public double[] getmultimatrix(double[][] matrix, double[] vector) {
        double[] vectorR = new double[this.numVar];
        for (int i = 0; i < this.numVar; i++) {
            vectorR[i] = 0;
            for (int j = 0; j < this.numVar; j++) {
                vectorR[i] = vectorR[i] + matrix[i][j] * vector[j];
            }
        }
        return vectorR;
    }

    public void getFitness(int numFun, double t, double [][][] vvv, int numCons, boolean Penalty) {//

        double epsilon = .0001;
       // double aa=1/(Math.sqrt(30));
        g = new ArrayList();
        h = new ArrayList();
        this.SumR = 0;
        fx = 0;

        switch (numFun) {
            case 1:

                //Sphere
                for (int i = 0; i < this.x.length; i++) {
                    fx = fx + Math.pow(x[i], 2);
                }

                break;
            case 2:
                //Rastrigin

                double sum = 0;
                for (int i = 0; i < this.x.length; i++) {
                    sum += Math.pow(x[i], 2) - 10 * Math.cos(2 * Math.PI * x[i]);
                }
                fx = 10 * ((double) this.x.length) + sum;

                break;
            case 3:
                //Ackley

               double ss = 0;
               double s=0;
                for (int i = 0; i < this.x.length; i++) {
                    s += Math.pow(x[i], 2);
                    ss += Math.cos(2 * Math.PI * x[i]);
                }
                fx = -20 * Math.exp(-0.2 * Math.sqrt((1 / (double) this.x.length) * s)) - Math.exp((1 / (double) this.x.length) * ss) + Math.exp(1) + 20;

                break;

            case 4: //Rosenbroke

                for (int i = 0; i < (this.x.length) - 1; i++) {
                    fx += 100 * (Math.pow(x[i + 1] - Math.pow(x[i], 2), 2)) + Math.pow(x[i] - 1, 2);
                }

                break;
            case 5:
                //schaffern2  , it is only for two, we may want to extend for n variables  

                nom = Math.pow(Math.sin(Math.abs(Math.pow(x[0], 2) - Math.pow(x[1], 2))), 2);
                denom = Math.pow((1 + (0.001 * (Math.pow(x[0], 2) + Math.pow(x[1], 2)))), 2);

                fx = 0.5 + (nom / denom);
                break;
        }

        double  [] ga = new double [numCons];
       
       
        for (int j = 0; j < numCons; j++) {
                    double param=0;
                    for (int i = 0; i < this.numVar; i++) {
                       param= param+ vvv[j][(int) t][i+1]*x[i];

                    }
                ga[j]=param-vvv[j][(int) t][0];
                g.add(ga[j]);
            
              }
      

  
        for (int i = 0; i < g.size(); i++) {
            if (g.get(i) > 0) {
                this.SumR += g.get(i);
            }
        }

        for (int i = 0; i < h.size(); i++) {
            if (Math.abs(h.get(i)) - epsilon > 0) {
                this.SumR += Math.abs(h.get(i));
            }
        }

    }
    
    public void getFitnessPenalty(int numFun, double t,double [][][]vvv,int numCons, double fmin, double fmax, double r, double cmax) {//

        double epsilon = .0001;
        g = new ArrayList();
        h = new ArrayList();
        this.SumR = 0;
        double v=0;        
        fx = 0;

        switch (numFun) {
            case 1:

                //Sphere
                for (int i = 0; i < this.x.length; i++) {
                    fx = fx + Math.pow(x[i], 2);
                }

                break;
            case 2:
                //Rastrigin

                double sum = 0;
                for (int i = 0; i < this.x.length; i++) {
                    sum += Math.pow(x[i], 2) - 10 * Math.cos(2 * Math.PI * x[i]);
                }
                fx = 10 * ((double) this.x.length) + sum;

                break;
            case 3:
                //Ackley

               double ss = 0;
               double s=0;
                for (int i = 0; i < this.x.length; i++) {
                    s += Math.pow(x[i], 2);
                    ss += Math.cos(2 * Math.PI * x[i]);
                }
                fx = -20 * Math.exp(-0.2 * Math.sqrt((1 / (double) this.x.length) * s)) - Math.exp((1 / (double) this.x.length) * ss) + Math.exp(1) + 20;

                break;

            case 4: //Rosenbroke

                for (int i = 0; i < (this.x.length) - 1; i++) {
                    fx += 100 * (Math.pow(x[i + 1] - Math.pow(x[i], 2), 2)) + Math.pow(x[i] - 1, 2);
                }

                break;
            case 5:
                //schaffern2  , it is only for two, we may want to extend for n variables  

                nom = Math.pow(Math.sin(Math.abs(Math.pow(x[0], 2) - Math.pow(x[1], 2))), 2);
                denom = Math.pow((1 + (0.001 * (Math.pow(x[0], 2) + Math.pow(x[1], 2)))), 2);

                fx = 0.5 + (nom / denom);
                break;
        }

        double [] ga = new double[numCons];
        

              for (int j = 0; j < numCons; j++) {
                    double param=0;
                    for (int i = 0; i < this.numVar; i++) {
                       param= param+ vvv[j][(int) t][i+1]*x[i];

                    }
                ga[j]=param-vvv[j][(int) t][0];
                g.add(ga[j]);
            
              }
   

        for (int i = 0; i < g.size(); i++) {
            if (g.get(i) > 0) {
                this.SumR += g.get(i);
                v=v+(g.get(i)/cmax);
            }
        }

        for (int i = 0; i < h.size(); i++) {
            if (Math.abs(h.get(i)) - epsilon > 0) {
                this.SumR += Math.abs(h.get(i));
                v=v+((Math.abs(h.get(i)) - epsilon)/cmax);

            }
            
            
        }
        double ncon= (double)(g.size()+ h.size());
        //For the Adaptative penalty       
//first is needed normalize         
       fnor=(fx-fmin)/(fmax-fmin);
       v=(1/(ncon))*v;
       
       //if all the individuals are feasibles 
       if(r==0)//if r is 0 all the population are infeasible
           fnor=v;
       if(r>0 && r<1)//when in the population we have feasible and infeasible solutions 
           fnor=Math.sqrt(Math.pow(fnor, 2)+Math.pow(v, 2))+((1-r)*v+r*fnor);

    }
    

    @Override
    public Individual clone() {
        Individual I = new Individual(this.numVar);
        System.arraycopy(this.x, 0, I.x, 0, this.x.length);
        I.fx = this.fx;
        I.fnor= this.fnor;
        I.SumR = this.SumR;
        I.g = new ArrayList();
        I.h = new ArrayList();
        for (int i = 0; i < this.g.size(); i++) {
            I.g.add(this.g.get(i));
        }

        return I;

    }

    @Override
    public int compareTo(Individual o) {
        //This comparison is used for epsilon constrained 
        //The sort is based on the maximun number of constraint violations 
        if (this.SumR >= o.SumR) {
            return -1;
        } else {
            return 1;
        }

    }

}
