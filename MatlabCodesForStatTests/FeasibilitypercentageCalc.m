clc;
clear;
Vmin=-5;
Vmax=5;
%VarSize=[1 2];
VarSize=[1 30];
c=10^6;
bfromFile=csvread(strcat('dC_01.csv'));
numChange=100;
for j=1:numChange
    j
a=(1/sqrt(30));
cc=0;
for i=1:c
    X=unifrnd(Vmin, Vmax, VarSize);
    g=-bfromFile(j)+a*sum(X);
    if g<=0
        cc=cc+1;
    end
end
    FeasPer(j)=(cc/c)*100;
end   
   
FeasPer