function b = bounds(X, e)
    e = 0.1;
    n = size(X, 2);
    b = zeros(n, 2);
    for i = 1:n
        b(i,:) = [min(X(:,i)) max(X(:,i))];
        diff = max( (b(i, 2) - b(i, 1)) * e, e );
        b(i,1) -= diff; b(i,2) += diff;
    endfor;
end;