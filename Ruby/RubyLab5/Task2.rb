def fun(x, i)
  (-1) ** (i + 1) * (Math.sin(i*x)/i)
end

def series(f, x, n)
  x_range = Math::PI/5..(4.0*Math::PI)/5
  n_range = 18..58
  error = 0.001

  unless x_range.include? x
    raise "'x' is out of range"
  end

  res, i, cur = 0.0, n, error
  if n_range.include? n
    (1..n).each do |i|
      cur = f.call(x, i)
      res += cur
    end
  else
    i = 0
    while cur.abs >= error
      cur = f.call(x, i)
      res += cur
      i += 1
    end
  end
  [res, i, cur]
end

sum, iter, error = series(method(:fun), 0.7, 18)

puts "Sum: #{sum}"
puts "Iterations: #{iter}"
puts "Error: #{error}"
