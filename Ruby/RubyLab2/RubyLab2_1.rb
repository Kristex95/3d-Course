def sum(arr)
  i = 0
  sum = 0
  while arr.length-1 != i
    sum += (arr[i][0] + arr[i+1][0])*(arr[i][1] - arr[i+1][1])
    i += 1
  end

  sum += (arr[i][0] + arr[0][0])*(arr[i][1] - arr[0][1])

  return (sum.abs).to_f/2
end

arr2D = [[114,146], [147,106], [167,84], [224,56], [277,47],
         [373,41], [441,45], [456,101], [453,147], [453,213],
         [425,245], [382,270], [330,273], [290,248], [300,199],
         [238,195], [197,212], [151,209], [105,190], [104,163]]

puts sum(arr2D)
arr1 = [[1,1], [4,2], [5,3]]
puts sum(arr1)