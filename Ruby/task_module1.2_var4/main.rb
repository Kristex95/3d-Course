require 'test-unit'

class Abiturient
  @@id = 0
  attr_accessor :id, :surname, :name, :last_name, :address, :phone, :marks, :marks_sum

  def initialize(surname, name, lastName, address, phone, marks)
    @id = @@id
    @surname = surname
    @name = name
    @last_name = lastName
    @address = address
    @phone = phone
    @marks = marks
    @@id += 1
    @marks_sum = 0
    marks.each do |i|
      @marks_sum += i
    end
  end

  def toString()
    return puts "id: #{id}, Surname: #{surname}, Name #{name}, Last name: #{last_name}, Address: #{address}, Phone: #{phone}, Marks: #{marks}"
  end
end

class Manager
  attr_accessor :abiturientArray

  def initialize(abiturientArray)
    @abiturientArray = abiturientArray
  end

  def BadMarksStudents()
    sum = 0
    @abiturientArray.each do |i|
      has_bad_marks = false
      i.marks.each do |j|
        if j <= 50
          has_bad_marks = true
        end
      end
      if has_bad_marks
        sum += 1
        puts "--#{i.name} has some bad marks"
      end
    end
    puts
    return sum
  end

  def AbiturientsWithMarksMoreThanParam(param)
    sum = 0
    @abiturientArray.each do |i|
      if i.marks_sum>=param
        sum += 1
        puts "--#{i.name}'s marks sum is bigger than #{param}"
      end
    end
    puts
    return sum
  end

  def printBestNAbiturients(n)
    sum = 0
    @abiturientArray = @abiturientArray.sort_by{|i| -i.marks_sum}
    puts "Best #{n} abiturients"
    for i in (0...n)
      sum += 1
      puts "--#{@abiturientArray[i].name} #{@abiturientArray[i].surname}"
    end

    puts
    puts "Semi-pass marks sum"
    @abiturientArray.each do |i|
      if i.marks_sum/4 > 60 && i.marks_sum/4 < 90
        puts "--#{i.name} #{i.surname}"
      end
    end
    return sum
  end
end

class Test1 < Test::Unit::TestCase
  def setup
    abiturients = Array.new
    abiturients.push(Abiturient.new("Zakharenko", "Kiril", "Vladyslavovych", "Kyiv", 123456, [90, 80, 70, 35]))
    abiturients.push(Abiturient.new("Bondarevska", "Polina", "Oleksandrivna", "Kyiv", 234567, [100, 100, 100, 100]))
    abiturients.push(Abiturient.new("Melenko", "Oleksandr", "Vitaliyovych", "Chernivtsi", 345678, [80, 80, 60, 60]))
    abiturients.push(Abiturient.new("Plaksyk", "Michael", "Valeriyovych", "Vasilkiv", 345678, [80, 90, 60, 60]))
    abiturients.push(Abiturient.new("Bylokrynytckiy", "Oleksandr", "Oleksandrovych", "Kyiv", 345678, [80, 100, 50, 100]))

    @manager = Manager.new(abiturients)
  end

  def test_1
    assert_equal(2, @manager.BadMarksStudents())
  end

  def test_2
    assert_equal(2, @manager.AbiturientsWithMarksMoreThanParam(300))
  end

  def test_3
    assert_equal(2, @manager.printBestNAbiturients(2))
  end

end
