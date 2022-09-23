A = false
B = false
C = true
X = 60
Y = -10
Z = 4

def A()
  return !(A || B) && (A && !B)
end

def B()
  return (Z != Y) == (6 >= Y) && A || B && C && X >= 1.5
end

def C()
  return (8 - X * 2 <= Z) && (X ** 2 <=> Y ** 2) || (Z >= 15)
end

def D()
  return X > 0 && Y < 0 || Z >= (X * Y - (-Y / X)) + (-Z)
end

def E()
  return !(A || B && !(C || (!A || B)))
end

def F()
  return X ** 2 + Y ** 2 >= 1 && X >= 0 && Y >= 0
end

def G()
  return (A && (C && B <=> B || A) || C) && B
end

puts(A())
puts(B())
puts(C())
puts(D())
puts(E())
puts(F())
puts(G())