def func()
  print("Enter x: ")
  x = (gets.chomp).to_f
  print("Enter j: ")
  j = (gets.chomp).to_f
  result = Math.cos(24*(Math::PI/2))+((Math.tan((Math.log(x**3, Math::E)).abs)**5 + 4.2 * (10**-2.8))/(Math.sqrt((x+Math.exp(j)).abs)))
  p result
  return result
end

func()
