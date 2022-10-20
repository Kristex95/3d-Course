require 'test-unit'

def A(a, c, x)
  return -a * x - c
end

def B(a, c, x)
  return (x - a).to_f / -c
end

def C(a, b, c, x)
  return (b * x).to_f / (c - a).to_f
end

def F(val, iter, resHash)
  resHash[iter] = val
end

def printResult(hash)
  hash.each { |k, v| puts "result iter: #{k}, value: #{v} " }
end

def Main(a, b, c, startX, endX, deltaX)
  resHash = Hash.new
  x = startX

  while x<endX

    result = 0
    if c < 0 && x != 0
      result = A(a, c, x)
    elsif c > 0 && x == 0
      result = B(a, c, x)
    else
      result = C(a, b, c, x)
    end

    if a.truncate && b.truncate && c.truncate != 0
      F(result, x, resHash)
    else
      F(result.to_i, x, resHash)
    end

    x = x + deltaX
  end
  return resHash
end


=begin
@result = Array.new
puts "Enter a: "
a = gets
a = a.to_i
puts "Enter b: "
b = gets
b = b.to_i
puts "Enter c: "
c = gets
c = c.to_i
puts "Start X: "
@start_X = gets
@start_X = @start_X.to_i
puts "End X: "
@end_X = gets
@end_X = @end_X.to_i
puts "Delta X: "
@delta_X = gets
@delta_X = @delta_X.to_i

result = Main(a,b,c,@start_X,@end_X,@delta_X,)
printResult(result)
=end

class Test1 < Test::Unit::TestCase
  def test1
    hash = {0=>0.5, 1=>1, 2=>2, 3=>3, 4=>4}
    assert_equal( hash, Main(1,1,2,0,5,1))
  end

  def test2
    hash = {0=>0.6666666666666666, 1=>1.0, 2=>2.0, 3=>3.0, 4=>4.0}
    assert_equal( hash, Main(2,1,3,0,5,1))
  end
end