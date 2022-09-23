def y(x, n)
  return (((2 * x) ** (-1.0 / 3)).to_f / (x ** (2.0 / 3) - (3 * x) ** (-1.0 / 3))) - ((x ** (2.0 / n)).to_f / ((x ** (5.0 / 3)) - (x ** (2.0 / n)))) - ((x + 1).to_f / (x ** (2) - 4 * x + 3))
end

def z(x, n, c)
  return (Math.sin((9.0 / 8) * Math::PI + x.to_f / 4) ** 2) - (Math.sin((7.0 / n) * Math::PI + x.to_f / c.pow(x))) ** 2 + (Math.tan(2 * x - 1)) ** (-1.0 / x)
end

def t1(n, c)
  piece = (n - 1).to_f / (n + c)
  cur = 1.0
  while cur <= n
    puts y(cur, n)
    cur += piece
  end
end

def t2(n, c)
  piece = Math::PI - (Math::PI / n) / (3.0 / 2) * n + c
  cur = Math::PI / n
  while cur <= Math::PI
    puts z(cur, n, c)
    cur += piece
  end
end

def t3(n,c)
  piece = (c-2).to_f/2*n
  x = 2
  while x <= c
    f= 0
    if(x>2 && x < n)
      f = y(x, n)
    elsif x>n && x<2*n
      f = z(x,n,c)
    else
      f =  y(x, n) + z(x,n,c)
    end
  end
end
puts ("-----------Task 1-------------")
t1(5, 10)
puts ("-----------Task 2-------------")
t2(3, 1)
puts ("-----------Task 3-------------")
t2(1, 5)