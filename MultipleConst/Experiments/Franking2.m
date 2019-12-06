clc;
clear;
close all;
masterStr='';
runs=30;
numChange=100;


Str={'Penalty', 'Feasibility', 'Epsilon'};
alName={'Penalty', 'Feasibility', '$\epsilon$-Constrained'};
exName={'Hyperplane translation',...
    'Dynamic $\tau$',...
    'Hyperplane translation \& rotation'};
exFName={'Experiment1','Experiment2','Experiment3'};
sevName={'Small','Medium','Large'};
fName={'Function 1: Sphere','Function 2: Rastrigin','Function 3: Ackley','Function 4: Rosenbrock'};

for numFun=1:length(fName)

masterStr=[masterStr,newline,strcat('\hline\multicolumn{8}{c}{\textbf{',fName{numFun},'}}\\\hline \multirow{2}{*}{Experiment}&\multirow{2}{*}{Frequency}&')];
%masterStr=[masterStr,newline,' '];
s='';
for i=1:length(alName)
    s=strcat(s,'\multicolumn{2}{c}{',alName{i},'}&');
end
masterStr=[masterStr,newline,strcat(s(1:end-1),'\\&&')];s='';
for i=1:length(alName)
    s=strcat(s,'Rank&$\text{M\_off\_e}$&');
end
masterStr=[masterStr,newline,strcat(s(1:end-1),'\\\hline')];
for ex=1:length(exFName)

if ex~=3
    masterStr=[masterStr,newline,strcat('\multirow{',num2str(length(sevName)),'}{*}{',exName{ex},'}&')];
    sevN=length(sevName);
    MofPenalty=csvread(strcat(exFName{ex},'/small/',Str{1}, num2str(numFun), 'MerrorMultiple.csv'));
MofPenalty=[MofPenalty;csvread(strcat(exFName{ex},'/medium/',Str{1}, num2str(numFun), 'MerrorMultiple.csv'))];
MofPenalty=[MofPenalty;csvread(strcat(exFName{ex},'/large/',Str{1}, num2str(numFun), 'MerrorMultiple.csv'))];

MofFeasibility=csvread(strcat(exFName{ex},'/small/',Str{2}, num2str(numFun), 'MerrorMultiple.csv'));
MofFeasibility=[MofFeasibility;csvread(strcat(exFName{ex},'/medium/',Str{2}, num2str(numFun), 'MerrorMultiple.csv'))];
MofFeasibility=[MofFeasibility;csvread(strcat(exFName{ex},'/large/',Str{2}, num2str(numFun), 'MerrorMultiple.csv'))];

MofEpsilon=csvread(strcat(exFName{ex},'/small/',Str{3}, num2str(numFun), 'MerrorMultiple.csv'));
MofEpsilon=[MofEpsilon;csvread(strcat(exFName{ex},'/medium/',Str{3}, num2str(numFun), 'MerrorMultiple.csv'))];
MofEpsilon=[MofEpsilon;csvread(strcat(exFName{ex},'/large/',Str{3}, num2str(numFun), 'MerrorMultiple.csv'))];

    
readFs.Penalty=csvread(strcat(exFName{ex},'/small/',Str{1}, num2str(numFun), 'Fs.csv'));
readFs.Penalty=[readFs.Penalty;csvread(strcat(exFName{ex},'/medium/',Str{1}, num2str(numFun), 'Fs.csv'))];
readFs.Penalty=[readFs.Penalty;csvread(strcat(exFName{ex},'/large/',Str{1}, num2str(numFun), 'Fs.csv'))];

readFs.Feasibility=csvread(strcat(exFName{ex},'/small/',Str{2}, num2str(numFun), 'Fs.csv'));
readFs.Feasibility=[readFs.Feasibility;csvread(strcat(exFName{ex},'/medium/',Str{2}, num2str(numFun), 'Fs.csv'))];
readFs.Feasibility=[readFs.Feasibility;csvread(strcat(exFName{ex},'/large/',Str{2}, num2str(numFun), 'Fs.csv'))];

readFs.Epsilon=csvread(strcat(exFName{ex},'/small/',Str{3}, num2str(numFun), 'Fs.csv'));
readFs.Epsilon=[readFs.Epsilon;csvread(strcat(exFName{ex},'/medium/',Str{3}, num2str(numFun), 'Fs.csv'))];
readFs.Epsilon=[readFs.Epsilon;csvread(strcat(exFName{ex},'/large/',Str{3}, num2str(numFun), 'Fs.csv'))];

readSumCVs.Penalty=csvread(strcat(exFName{ex},'/small/',Str{1}, num2str(numFun), 'SumCVs.csv'));
readSumCVs.Penalty=[readSumCVs.Penalty;csvread(strcat(exFName{ex},'/medium/',Str{1}, num2str(numFun), 'SumCVs.csv'))];
readSumCVs.Penalty=[readSumCVs.Penalty;csvread(strcat(exFName{ex},'/large/',Str{1}, num2str(numFun), 'SumCVs.csv'))];

readSumCVs.Feasibility=csvread(strcat(exFName{ex},'/small/',Str{2}, num2str(numFun), 'SumCVs.csv'));
readSumCVs.Feasibility=[readSumCVs.Feasibility;csvread(strcat(exFName{ex},'/medium/',Str{2}, num2str(numFun), 'SumCVs.csv'))];
readSumCVs.Feasibility=[readSumCVs.Feasibility;csvread(strcat(exFName{ex},'/large/',Str{2}, num2str(numFun), 'SumCVs.csv'))];

readSumCVs.Epsilon=csvread(strcat(exFName{ex},'/small/',Str{3}, num2str(numFun), 'SumCVs.csv'));
readSumCVs.Epsilon=[readSumCVs.Epsilon;csvread(strcat(exFName{ex},'/medium/',Str{3}, num2str(numFun), 'SumCVs.csv'))];
readSumCVs.Epsilon=[readSumCVs.Epsilon;csvread(strcat(exFName{ex},'/large/',Str{3}, num2str(numFun), 'SumCVs.csv'))];
else
    masterStr=[masterStr,newline,strcat('',exName{ex},'&')];
    sevN=1;
    MofPenalty=csvread(strcat(exFName{ex},'/comb/',Str{1}, num2str(numFun), 'MerrorMultiple.csv'));
MofFeasibility=csvread(strcat(exFName{ex},'/comb/',Str{2}, num2str(numFun), 'MerrorMultiple.csv'));
MofEpsilon=csvread(strcat(exFName{ex},'/comb/',Str{3}, num2str(numFun), 'MerrorMultiple.csv'));
readFs.Penalty=csvread(strcat(exFName{ex},'/comb/',Str{1}, num2str(numFun), 'Fs.csv'));
readFs.Feasibility=csvread(strcat(exFName{ex},'/comb/',Str{2}, num2str(numFun), 'Fs.csv'));
readFs.Epsilon=csvread(strcat(exFName{ex},'/comb/',Str{3}, num2str(numFun), 'Fs.csv'));
readSumCVs.Penalty=csvread(strcat(exFName{ex},'/comb/',Str{1}, num2str(numFun), 'SumCVs.csv'));
readSumCVs.Feasibility=csvread(strcat(exFName{ex},'/comb/',Str{2}, num2str(numFun), 'SumCVs.csv'));
readSumCVs.Epsilon=csvread(strcat(exFName{ex},'/comb/',Str{3}, num2str(numFun), 'SumCVs.csv'));
end

for sev=1:sevN
if ex~=3
    if sev==1
        masterStr=[masterStr,strcat(sevName{sev},'&')];
    else
        masterStr=[masterStr,newline,strcat('&',sevName{sev},'&')];
    end
else
    masterStr=[masterStr,strcat(sevName{2},'&')];
end
ind=(sev-1)*runs;

mp=mean(MofPenalty(ind+1:ind+runs,1));
mf=mean(MofFeasibility(ind+1:ind+runs,1));
me=mean(MofEpsilon(ind+1:ind+runs,1));
sp=std(MofPenalty(ind+1:ind+runs,1));
sf=std(MofFeasibility(ind+1:ind+runs,1));
se=std(MofEpsilon(ind+1:ind+runs,1));
results=[mp,sp,mf,sf,me,se];

    
for i = 1:numChange
    for j = 1:runs+1
        for k = 1:length(alName)
            A(j,k,i) = k;
        end
    end
end

for i = 1:numChange
    for j = 1:runs
        for k = 1:length(alName)
            if k == 1
              compF1 = readFs.Feasibility(ind+j,i);
                compCV1 = readSumCVs.Feasibility(ind+j,i);
            elseif k == 2
                compF1 = readFs.Epsilon(ind+j,i);
                compCV1 = readSumCVs.Epsilon(ind+j,i);
            elseif k == 3
                compF1 = readFs.Penalty(ind+j,i);
                compCV1 = readSumCVs.Penalty(ind+j,i);
            end
            for l = 1:length(alName)
                if l == 1
                    compF2 = readFs.Feasibility(ind+j,i); 
                    compCV2 = readSumCVs.Feasibility(ind+j,i);
                elseif l == 2
                    compF2 = readFs.Epsilon(ind+j,i);
                    compCV2 = readSumCVs.Epsilon(ind+j,i);

                elseif l == 3
                compF2 = readFs.Penalty(ind+j,i);
                compCV2 = readSumCVs.Penalty(ind+j,i);
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
    for j = 1:length(alName)
        sum1 = sum(A(1:runs,j,i));
        for k = 1:length(alName)
            sum2 = sum(A(1:runs,k,i));
            if k <= j
               if sum1 < sum2
                   temp = A(runs+1,j,i);
                   A(runs+1,j,i) = A(runs+1,k,i);
                   A(runs+1,k,i) = temp;
               end
            end
        end

        for j = 1:length(alName)
           semiFinal(i,j) =  A(runs+1,j,i);
        end
    end
end
for i = 1:length(alName)
   final(i) = i;
end
for j = 1:length(alName)
   sum1 = sum(semiFinal(:,j));
   for k = 1:length(alName)
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
for j=1:length(alName)
   masterStr=strcat(masterStr,num2str(final(j)),'&',num2str(results(2*j-1)),'($\pm$',num2str(results(2*j)),')&'); 
end
masterStr=strcat(masterStr(1:end-1),'\\');
end
end
end
masterStr=[masterStr,newline,'\hline'];