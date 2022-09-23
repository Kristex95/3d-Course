def diap(p, t, r)
    sum = (p ** r) * (1 - 1/ (p ** t))
    return sum
end

r = 10;
p = 6;
t = 15;

print diap(p, t, r)