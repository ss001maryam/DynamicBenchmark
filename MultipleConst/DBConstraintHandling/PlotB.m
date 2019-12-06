clc;
close all;
bLarge=csvread(strcat('Large.csv'));
bMed=csvread(strcat('Med.csv'));
bSmall=csvread(strcat('Small.csv'));

plot(bLarge,'color', [0 1 0.5],'LineWidth',1.8);
hold on;

plot(bMed, '--r','LineWidth',1.8);
hold on;

plot(bSmall, '-.b','LineWidth',1.8);

legend( 'bLarge','bMed', 'bSmall','Location', 'Northeast');
