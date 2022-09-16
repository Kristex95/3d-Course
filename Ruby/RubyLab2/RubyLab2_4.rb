def DecimalToBinary(val)
  arr = Array.new
  while true
    if val % 2 == 0
      arr.push(0)
    else
      arr.push(1)
    end
    val /= 2
    if val == 0
      break
    end
  end
  arr = arr.reverse
  for i in (0..arr.length)
    print arr[i]
  end
  return
end

val = 334
DecimalToBinary(val)
