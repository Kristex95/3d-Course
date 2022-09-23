def func(x)
  case x
  when -4..0
    if x==-4
      puts("Error")
    end
    return ((x - 2).abs / (x**2 * Math.cos(x)))**(1/7)
  when 0..12
    return 1/((Math.tan(x + (1/Math::E)))/(Math.sin(x)**2))**(1/(7/2))
  else
    return 1/(1 + x/ (1 + x/ (1 + x)))
  end
end

puts(func(-5))
