function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);

a1 = [ones(m, 1) X];
z2 = a1 * Theta1';
a2 = [ones(m, 1) sigmoid(z2)];
z3 = a2 * Theta2';
a3 = h = sigmoid(z3);

vy = zeros(m, num_labels);
for i = 1:m
    vy(i,y(i)) = 1;
endfor

J = sum(sum(-vy .* log(h) - (1 - vy) .* log(1 - h))) / m;

n1 = size(Theta1, 2);
n2 = size(Theta2, 2);
J += lambda * (sum(sumsq( Theta1(:,2:n1) )) + sum(sumsq( Theta2(:,2:n2) ))) / (2 * m) ;

Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

format long g;

d3 = a3 - vy;
d2 = d3 * Theta2 .* [ones(m,1) sigmoidGradient(z2)];

Theta2_grad = 1/m * ( d3' * a2 );
Theta1_grad = 1/m * ( d2(:,2:end)' * a1 );

reg2 = lambda / m * Theta2;
Theta2_grad(:,2:end) += reg2(:,2:end);
reg1 = lambda / m * Theta1;
Theta1_grad(:,2:end) += reg1(:,2:end);


% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
