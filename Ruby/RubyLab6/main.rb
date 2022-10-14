require 'date'

class Library
  def initialize
    @records = Array.new
  end

  def newRecord(surname, orderDate, issueDate, book)
    @records.push(Record.new(surname, orderDate, issueDate, book))
  end

  def printRecords
    @records.each { |i| p "#{i.surname} #{i.orderDate} #{i.issueDate} #{i.book}" }
  end

  def fastestIssue
    if !@records.empty?
      fastest = @records.at(0).issueDate - @records.at(0).orderDate

      (1...@records.length).each { |rec|
        if @records[rec].issueDate != nil
          newTime = @records[rec].issueDate - @records[rec].orderDate
          if (newTime < fastest)
            fastest = newTime
          end
        end
      }
    end
    p fastest
  end

  def notIssued
    counter = 0
    @records.each { |rec|
      if rec.issueDate == nil
        counter += 1
      end
    }
    p counter
  end

  def mostFrequentReader
    hash = Hash[]
    @records.each { |rec|
      hash[rec.surname] = 0
    }
    @records.each { |rec| hash[rec.surname] += 1 }
    orderCount = 0;
    hash.each { |surname, orders|
      if orders > orderCount
        orderCount = orders
      end
    }
    p hash.key(orderCount)
  end

  def mostFrequentBook
    hash = Hash[]
    @records.each { |rec|
      hash[rec.book] = 0
    }
    @records.each { |rec| hash[rec.book] += 1 }
    orderCount = 0;
    hash.each { |book, orders|
      if orders > orderCount
        orderCount = orders
      end
    }
    p hash.key(orderCount)
  end

  def bestThreeBooksOrders
    hash = Hash[]
    @records.each { |rec|
      hash[rec.book] = 0
    }
    @records.each { |rec| hash[rec.book] += 1 }
    newHash = hash.sort_by{|k, v| -v}
    counter = 0
    newHash.each { |key, value| p "#{key} #{value}"
    if counter == 2
      break
    end
    counter+=1
    }
  end

  class Record
    attr_accessor :surname, :orderDate, :issueDate, :book

    def initialize(surname, orderDate, issueDate, book)
      @surname = surname
      @orderDate = orderDate
      @issueDate = issueDate
      @book = book
    end

    def getSurname
      @surname
    end

    def getOrderDate
      @orderDate
    end

    def getIssueDate
      @issueDate
    end
  end
end

library = Library.new
library.newRecord("Zakharenko", Date.new(2004, 03, 12), Date.new(2004, 06, 22), "History")
library.newRecord("Zakharenko", Date.new(2003, 05, 12), Date.new(2004, 05, 12), "Math")
library.newRecord("Bondarevska", Date.new(2005, 05, 25), Date.new(2007, 01, 01), "Math")
library.newRecord("Bondarevska", Date.new(2003, 02, 17), Date.new(2004, 05, 13), "Biology")
library.newRecord("Bondarevska", Date.new(2003, 05, 12), Date.new(2004, 07, 01), "Geography")
library.newRecord("Plaksyk", Date.new(2003, 05, 12), nil, "Math")
library.newRecord("Plaksyk", Date.new(2003, 05, 12), Date.new(2004, 06, 02), "Geography")
p "The fastest issue"
library.fastestIssue
puts "\n"
p "How many orders were not issued"
library.notIssued
puts "\n"
p "Most frequent reader"
library.mostFrequentReader
puts "\n"
p "Most frequent book for order"
library.mostFrequentBook
puts "\n"
p "Best three books and number of orders for them"
library.bestThreeBooksOrders