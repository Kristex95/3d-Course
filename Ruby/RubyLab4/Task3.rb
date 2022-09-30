require 'test-unit'

class Test1 < Test::Unit::TestCase
  attr_accessor :s,:result, :arr_A, :arr_b
  def setup
    @s = 5
    @arr_A = create_A(5, @s)
    @arr_b = create_B(@s)
    @result  = [0.5, 0.3, 0.1, -0.1, -0.3]


  end
  def test2
    c = Array.new @s
    c = gauss(@arr_A, @arr_b, c, @s)
    assert_equal(c, @result)

  end
end

size = 5
kk = 5

def create_A(kk, size)
  arr_a = (0...size).map{ Array.new(size)}
  (0..size - 1).each { |i|
    (0..size - 1).each { |j|
      if i == j
        arr_a[i][j] = 2.0
      else
        arr_a[i][j] = kk+2.to_f
      end
    }
  }
  arr_a
end
arr_a = create_A(kk, size)
p arr_a
def create_B (size)
  arr_b = Array.new size
  (0..size-1).each { |i| arr_b[i] = i+1.to_f }
  arr_b
end
arr_b = create_B(size)
p arr_b


c = Array.new(size )

def gauss(arr_a, arr_b, c, size)
  (0..size - 1).each { |l|
    (l + 1..size - 1).each { |j|
      d = arr_a[j][l] / arr_a[l][l]
      (l..size - 1).each { |i|
        arr_a[j][i] = arr_a[j][i] - d * arr_a[l][i]
      }
      arr_b[j] = arr_b[j] - d * arr_b[l]
    }
  }

  range = size-1..0
  (range.first).downto(range.last).each {|l|
    d = 0
    (l + 1..size - 1).each { |j|
      s = arr_a[l][j] * c[j]
      d = d + s
    }
    c[l] = (arr_b[l] - d) / arr_a[l][l]
  }
  (0..size-1).each{|i| c[i] = c[i].round(1)}
  c
end
c = gauss(arr_a, arr_b, c, size)
puts "roots for this system: #{c}"


