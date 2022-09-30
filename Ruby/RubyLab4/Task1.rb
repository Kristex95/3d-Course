$arr = Array.new(12) {|e| e = e + 1}
buff = $arr[11]
for number in -10..0
  $arr[number.abs + 1] = $arr[number.abs]
end
$arr[0] = buff

for number in 0..11
  p $arr[number]
end

require 'test-unit'

class Test1 < Test::Unit::TestCase
  def test2
    assert_equal($arr, [12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
  end
end