function [C, sigma] = dataset3Params(X, y, Xval, yval)
%EX6PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = EX6PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

valueSet = [0.01 0.03 0.1 0.3 1 3 10 30];
results = zeros(8,8)

minErr = 1000000

for i = 1:8
    for j = 1:8
        C_to_check = valueSet(i);
        sigma_to_check = valueSet(j);

        fprintf("Checking C = %f , sigma = %f", C_to_check, sigma_to_check);

        model = svmTrain(X, y, C_to_check, @(x1, x2) gaussianKernel(x1, x2, sigma_to_check));

        predictions = svmPredict(model, Xval);
        err = mean(double(predictions ~= yval));
        if (err < minErr)
            fprintf("Got new best err = %f , C = %f, sigma = %f\n", err, C_to_check, sigma_to_check);
            minErr = err;
            C = C_to_check;
            sigma = sigma_to_check;
        endif
    endfor
endfor

% =========================================================================

end
