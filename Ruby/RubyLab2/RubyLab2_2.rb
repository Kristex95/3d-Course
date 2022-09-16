def diap(p, t, r)
    sum = (p ** r) * (1 - 1/ (p ** t))
    return sum
end

r = 0;
p = 5;
t = 7;

print diap(p, t, r)