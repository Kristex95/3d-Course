require 'matrix'
require 'test-unit'

class Test1 < Test::Unit::TestCase
  attr_accessor :arr1,:arr2,:matrix3
  def setup
    @arr1 = (0..2).map { Array.new(2) }
    for i in (0..2)
      for j in (0..2)
        @arr1[i][j] = 1
      end
    end
    @arr2 = (0..2).map { Array.new(2) }
    for i in (0..2)
      for j in (0..2)
        @arr2[i][j] = 2
      end
    end
    @matrix1 = Matrix[*@arr1]
    @matrix2 = Matrix[*@arr2]
    @matrix3 = matrixmultiplicator(@matrix1, @matrix2, 3)
  end
  def test2
    arr = (0..2).map { Array.new(2) }
    for i in (0..2)
      for j in (0..2)
        arr[i][j] = 6
      end
    end
    matrix = Matrix[*arr]
    assert_equal(@matrix3, matrix)
  end
end

class Matrix
  def []=(i, j, value)
    @rows[i][j] = value
  end
end

def matrixmultiplicator(first_matrix, second_matrix, size)
  arr = (0..size-1).map { Array.new(size-1) }
  for i in (0..size-1)
    for j in (0..size-1)
      arr[i][j] = 0
    end
  end
  result_matrix = Matrix[*arr]
  for i in (0..size-1)
    for j in (0..size-1)
      for k in (0..size-1)
        result_matrix[i, j] += first_matrix[i, k] * second_matrix[k, j]
      end
    end
  end
  return result_matrix
end

arr1 = (0..7).map { Array.new(7) }
for i in (0..7)
  for j in (0..7)
    if i == j
      arr1[i][j] = 1
    else
      arr1[i][j] = rand(10)
    end
  end
end

matrix = Matrix[*arr1]

arr2 = (0..7).map { Array.new(7) }
for i in (0..7)
  for j in (0..7)
    if i == j
      arr2[i][j] = 1
    else
      arr2[i][j] = rand(10)
    end
  end
end
matrix2 = Matrix[*arr2]

matrix3 = matrixmultiplicator(matrix, matrix2, 8)
puts matrix
puts matrix2
puts matrix3


