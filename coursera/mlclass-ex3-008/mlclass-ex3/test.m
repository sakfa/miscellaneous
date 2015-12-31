load('ex3data1.mat');
load('ex3weights.mat');
pred = predict(Theta1, Theta2, X);

[y pred]


%load('ex3data1');
%
%num_labels = 10;
%oneVsAll(X, y, num_labels, 0.1);
%




%X = [
%    2 3
%    3 4
%    5 6
%    7 8
%];
%X = [ ones(4, 1) X ];
%y = [ 10 ; 11 ; 12 ; 13 ];
%theta = [1 ; 1 ; 1];
%lambda = 10;
%
%[J, grad] = costFunctionReg(theta, X, y, lambda);
%J
%grad
%[J, grad] = lrCostFunction(theta, X, y, lambda);
%J
%grad



