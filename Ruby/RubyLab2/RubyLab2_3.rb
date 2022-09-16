class String
  alias :each :each_char
end

def BinaryToDecimal(val)
  valS = val.to_s
  valSReverse = valS.reverse
  counter = 0
  sum = 0
  for i in (0..valSReverse.length)
    sum += (valSReverse[i].to_i) * 2**counter
    counter += 1
  end
  print val
  print " "
  return sum
end

binary = 1110;
binary2 = 111100010011;
puts BinaryToDecimal(binary)
puts BinaryToDecimal(binary2)
