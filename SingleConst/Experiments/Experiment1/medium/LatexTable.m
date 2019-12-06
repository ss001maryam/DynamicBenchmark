
clc;
clear;
close all;
numChange=100;

Str=["Penalty","Feasibility", "Epsilon"];

%change the function number here, then you can read the relevent file
numFun=1;

fvalstore=csvread(strcat('Best_Know', num2str(numFun), 'Fxs.csv'));
%solution=csvread(strcat('Best_Know_Comb', num2str(numFun), 'Solutions.csv'));

readFs.Penalty=csvread(strcat(Str(1), num2str(numFun), 'Fs.csv'));
readFs.Feasibility=csvread(strcat(Str(2), num2str(numFun), 'Fs.csv'));
readFs.Epsilon=csvread(strcat(Str(3), num2str(numFun), 'Fs.csv'));



readSumCVs.Penalty=csvread(strcat(Str(1), num2str(numFun), 'SumCVs.csv'));
readSumCVs.Feasibility=csvread(strcat(Str(2), num2str(numFun), 'SumCVs.csv'));
readSumCVs.Epsilon=csvread(strcat(Str(3), num2str(numFun), 'SumCVs.csv'));


for j=1:numChange
   MeanSumCVs.Penalty(j)=mean(readSumCVs.Penalty(:,j));
   MeanSumCVs.Feasibility(j)=mean(readSumCVs.Feasibility(:,j));
   MeanSumCVs.Epsilon(j)=mean(readSumCVs.Epsilon(:,j));
   
end
fcv=csvread(strcat('Best_Know', num2str(numFun), 'SumCV.csv'));




b=csvread(strcat('dC_01.csv'));
Vmin=-5;
Vmax=5;
VarSize=[1 30];
FeasPer=zeros(0,100);

c=10^6;
for j=46:46
    j
a=(1/sqrt(30));
cc=0;
for i=1:c
    X=unifrnd(Vmin, Vmax, VarSize);
    g=-b(j)+a*sum(X);
    if g<=0
        cc=cc+1;
    end
end
    FeasPer(j)=(cc/c)*100;
    
   mp(j)=mean(readFs.Penalty(:,j));
   mf(j)=mean(readFs.Feasibility(:,j));
   me(j)=mean(readFs.Epsilon(:,j));
   sp(j)=std(readFs.Penalty(:,j));
   sf(j)=std(readFs.Feasibility(:,j));
   se(j)=std(readFs.Epsilon(:,j));
   Latex =strcat(num2str(j),'    &  ',num2str(b(j)),'    &  ',num2str(FeasPer(j)),'    &  ',num2str(fvalstore(j)),'    &  ',num2str(mp(j)),'($\pm$',num2str(sp(j)),')',' & ',num2str(mf(j)),'($\pm$',num2str(sf(j)),')','  & ',num2str(me(j)),'($\pm$',num2str(se(j)),')')

end 

