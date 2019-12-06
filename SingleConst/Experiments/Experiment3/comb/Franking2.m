clc;
clear;
close all;

runs=30;
numChange=100;
numFun=1;
Str=["Penalty", "Feasibility", "Epsilon"];


MofPenalty=csvread(strcat(Str(1), num2str(numFun), 'Merror.csv'));
MofFeasibility=csvread(strcat(Str(2), num2str(numFun), 'Merror.csv'));
MofEpsilon=csvread(strcat(Str(3), num2str(numFun), 'Merror.csv'));


    mp=mean(MofPenalty);
    mf=mean(MofFeasibility);
    me=mean(MofEpsilon);
    sp=std(MofPenalty);
    sf=std(MofFeasibility);
    se=std(MofEpsilon);
    results=[mp,mf,me,sp,sf,se]

    
readFs.Penalty=csvread(strcat(Str(1), num2str(numFun), 'Fs.csv'));
readFs.Feasibility=csvread(strcat(Str(2), num2str(numFun), 'Fs.csv'));
readFs.Epsilon=csvread(strcat(Str(3), num2str(numFun), 'Fs.csv'));



readSumCVs.Penalty=csvread(strcat(Str(1), num2str(numFun), 'SumCVs.csv'));
readSumCVs.Feasibility=csvread(strcat(Str(2), num2str(numFun), 'SumCVs.csv'));
readSumCVs.Epsilon=csvread(strcat(Str(3), num2str(numFun), 'SumCVs.csv'));

for i = 1:numChange
    for j = 1:31
        for k = 1:3
            A(j,k,i) = k;
        end
    end
end

for i = 1:numChange
    for j = 1:30
        for k = 1:3
            if k == 1
              compF1 = readFs.Feasibility(j,i);
                compCV1 = readSumCVs.Feasibility(j,i);
            elseif k == 2
                compF1 = readFs.Epsilon(j,i);
                compCV1 = readSumCVs.Epsilon(j,i);
            elseif k == 3
                compF1 = readFs.Penalty(j,i);
                compCV1 = readSumCVs.Penalty(j,i);
            end
            for l = 1:3
                if l == 1
                    compF2 = readFs.Feasibility(j,i); 
                    compCV2 = readSumCVs.Feasibility(j,i);
                elseif l == 2
                    compF2 = readFs.Epsilon(j,i);
                    compCV2 = readSumCVs.Epsilon(j,i);

                elseif l == 3
                compF2 = readFs.Penalty(j,i);
                compCV2 = readSumCVs.Penalty(j,i);
                end
                if l < k
                    if compCV1 == 0 && compCV2 == 0
                        if compF1 < compF2
                            temp = A(j,k,i);
                            A(j,k,i) = A(j,l,i);
                            A(j,l,i) = temp;
                        end
                    else
                       if compCV1 < compCV2
                            temp = A(j,k,i);
                            A(j,k,i) = A(j,l,i);
                            A(j,l,i) = temp;
                       end
                    end
                end
            end
        end
    end
    for j = 1:3
        sum1 = sum(A(1:30,j,i));
        for k = 1:3
            sum2 = sum(A(1:30,k,i));
            if k <= j
               if sum1 < sum2
                   temp = A(31,j,i);
                   A(31,j,i) = A(31,k,i);
                   A(31,k,i) = temp;
               end
            end
        end

        for j = 1:3
           semiFinal(i,j) =  A(31,j,i);
        end
    end
end
for i = 1:3
   final(i) = i;
end
for j = 1:3
   sum1 = sum(semiFinal(:,j));
   for k = 1:3
      sum2 = sum(semiFinal(:,k));
      if k <= j
          if sum1 < sum2
              temp = final(j);
              final(j) = final(k);
              final(k) = temp;
          end
      end
   end
end