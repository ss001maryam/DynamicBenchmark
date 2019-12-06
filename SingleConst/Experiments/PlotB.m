clc;
close all;
bLarge=csvread(strcat('Large.csv'));
bMed=csvread(strcat('Med.csv'));
bSmall=csvread(strcat('Small.csv'));
%btest=csvread(strcat('dC_01.csv'));
plot(bLarge,'color', [0 1 0.5],'LineWidth',1.8);
hold on;

plot(bMed, '--r','LineWidth',1.8);
hold on;

plot(bSmall, '-.b','LineWidth',1.8);

%hold on;
%plot(btest, 'color', [0 1 1],'LineWidth',1.8);

legend( 'b\_Large','b\_Med', 'b\_Small','Location', 'Northeast');
xlabel('Time', 'FontSize', 18);
ylabel('b\_values', 'FontSize', 18);
set(gca,'FontSize',22);
axis tight;
