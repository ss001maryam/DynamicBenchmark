clc;
clear;
close all;

numChange=100;
Setting = "CombL1000";

Str=["Penalty","Feasibility", "Epsilon"];
runs=2;

%change the function number here, then you can read the relevent file
numFun=2;
%Each change

fvalstore=csvread(strcat('Best_Know', num2str(numFun), 'Fxs.csv'));
%solution=csvread(strcat('Best_Know_Comb', num2str(numFun), 'Solutions.csv'));

readFs.Penalty=csvread(strcat(Str(1), num2str(numFun), 'Fs.csv'));
readFs.Feasibility=csvread(strcat(Str(2), num2str(numFun), 'Fs.csv'));
readFs.Epsilon=csvread(strcat(Str(3), num2str(numFun), 'Fs.csv'));



readSumCVs.Penalty=csvread(strcat(Str(1), num2str(numFun), 'SumCVs.csv'));
readSumCVs.Feasibility=csvread(strcat(Str(2), num2str(numFun), 'SumCVs.csv'));
readSumCVs.Epsilon=csvread(strcat(Str(3), num2str(numFun), 'SumCVs.csv'));

    
%{
p.Penalty=zeros(1,200);
p.Feasibility=zeros(1,200);
p.Epsilon=zeros(1,200);

 for j=1:numChange
    
  for i=1:runs
      
      if readSumCVs.Penalty(i,j)>0
      readFs.Penalty(i,j)=NaN;
   p.Penalty(j)=p.Penalty(j)+1;
      end
 
   
      if readSumCVs.Feasibility(i,j)>0
      readFs.Feasibility(i,j)=NaN;
       p.Feasibility(j)=p.Feasibility(j)+1;
      end 
   

      if readSumCVs.Epsilon(i,j)>0
      readFs.Epsilon(i,j)=NaN;
      p.Epsilon(j)=p.Epsilon(j)+1;
      end 
  end
end      
%}
for j=1:numChange
   MeanFs.Penalty(j)=mean(readFs.Penalty(:,j));
   MeanFs.Feasibility(j)=mean(readFs.Feasibility(:,j));
   MeanFs.Epsilon(j)=mean(readFs.Epsilon(:,j));
end

%taking meansumcv

for j=1:numChange
   MeanSumCVs.Penalty(j)=mean(readSumCVs.Penalty(:,j));
   MeanSumCVs.Feasibility(j)=mean(readSumCVs.Feasibility(:,j));
   MeanSumCVs.Epsilon(j)=mean(readSumCVs.Epsilon(:,j));
end




%% Figure 1, for each time change
%plot A
m=2;


subplot(m,1,1);
plot(fvalstore(1:numChange), '.-k');
hold on;


subplot(m,1,1);
plot(MeanFs.Penalty(:), '-.b','LineWidth',1.8);
hold on;

subplot(m,1,1);
plot(MeanFs.Feasibility(:), '--r','LineWidth',1.8);
hold on;

subplot(m,1,1);
plot(MeanFs.Epsilon(:),'color', [0 1 0.5],'LineWidth',1.8);
hold on;

ylabel('Objective Function', 'FontSize', 18);
set(gca,'FontSize',22);

axis tight;

legend( 'Best_known','Penalty', 'Feasibility', 'Epsilon','Location', 'Northeast');

%sum of constraint violation

fcv=csvread(strcat('Best_Know', num2str(numFun), 'SumCV.csv'));

subplot(m,1,2);
bar(fcv(1:numChange), 'stacked','k');
hold on;

subplot(m,1,2);
bar(MeanSumCVs.Penalty(:), 'stacked', 'b');%-.
hold on;

subplot(m,1,2);
bar(MeanSumCVs.Feasibility(:), 'stacked', 'r');%--
hold on;

subplot(m,1,2);
bar(MeanSumCVs.Epsilon(:), 'stacked','FaceColor',[0 1 0.5] ,'EdgeColor', [0 1 0.5] );
hold on;

xlabel('Time', 'FontSize', 18);
ylabel('Sum of CV', 'FontSize', 18);
set(gca,'FontSize',22);
axis tight;










