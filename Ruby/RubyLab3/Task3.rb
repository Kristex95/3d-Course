def t1(x)
  sum = 1
  for i in 1..10
    sum += (-1**i)*((i+1)/(i+2))*x**i
  end
  return sum
end

def t2()
  sum = 1
  for i in 1..8
    sum += 1/3**i
  end
  return sum
end

def t5(n)
  return n>0 ? Math.sqrt(2+t5(n-1)):0
end

puts(t1(2))
puts(t2())
puts(t5(2))
