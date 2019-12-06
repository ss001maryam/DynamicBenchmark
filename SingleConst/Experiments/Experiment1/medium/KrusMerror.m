clc;
clear;
close all;
numFun=4;
runs=30;

Str=["Penalty", "Feasibility", "Epsilon"];


   readFs.Penalty=csvread(strcat(Str(1), num2str(numFun), 'Merror.csv'));
   readFs.Feasibility=csvread(strcat(Str(2), num2str(numFun), 'Merror.csv'));
   readFs.Epsilon=csvread(strcat(Str(3), num2str(numFun), 'Merror.csv'));

   

Function.y(1,:)=readFs.Penalty(:,1);
Function.y(2,:)= readFs.Feasibility(:,1);
Function.y(3,:)=readFs.Epsilon(:,1);
%Function.y(4,:)=runsStochasticRanking(:)
for i=1:3
x(i,:)=Function.y(i,:);

end

[p,tbl,stats] = kruskalwallis(x',{'Penalty','Feasibility-Rules','Epsilon-Constrained' }) %Los algoritmos comparados

c=multcompare(stats,'ctype','bonferroni','display','on');
title('Function 1, Frequency 500 evals, b0=10, -lk=uk=15','FontSize',18);

AverageEpsilon = mean(Function.y(1,:));
AverageFeasibility = mean(Function.y(2,:));
AveragePenalty = mean(Function.y(3,:));



