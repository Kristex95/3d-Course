def t1(eps)
  sum = 0
  num = 1
  n = 2
  while num >= eps
    num = ((n - 1).factorial.to_f / (n + 1).factorial.to_f) ** (n * (n + 1))
    n += 1
    sum += num
  end
  return sum
end

def t2(eps)
  n = 1
  sum = 0
  num = 1
  while num.abs > eps
    num = -1.pow(n) * (1.0 / n.pow(2))
    sum += num
    n += 1
  end
  puts "#{sum} #{Math::PI ** 2 / 12}"
end

def t3(eps)
  sum = 0
  num = 1
  n = 1
  while num >= eps
    num = ((3 * n - 1).factorial * (3 * n).factorial).to_f / ((4 * n).factorial * 3.pow(2 * n) * (2 * n).factorial)
    n += 1
    sum += num
  end
  return sum
end

class Integer
  def factorial()
    self > 1 ? self * (self - 1).factorial : 1
  end
end

puts(t1(0.00001))
puts nil
puts(t2(0.00001))
puts nil
puts(t3(0.00001))