package main

import (
	"container/list"
	"sync"
	"time"
)

var wg sync.WaitGroup

func main() {
	var clientsQueue = list.New()
	var lock sync.Mutex

	for i := 0; i < 10; i++ {
		wg.Add(10)
		go phoneToOperator(i, clientsQueue, &lock)
	}

	for i := 0; i < 2; i++ {
		go workWithCustomer(i, clientsQueue, &lock)
	}

	wg.Wait()
}

func phoneToOperator(customer int, clientsList *list.List, lock *sync.Mutex) {
	for true {
		lock.Lock()
		println("#", customer, " calling;")
		clientsList.PushBack(customer)
		lock.Unlock()

		time.Sleep(5000 * time.Millisecond)

		lock.Lock()
		found := findElementInList(clientsList, customer)
		if found {
			println("Customer #", customer, " dropped call;")
			deleteElementFromList(clientsList, customer)
			lock.Unlock()

			time.Sleep(10000 * time.Millisecond)

		} else {
			lock.Unlock()
			wg.Done()
			break
		}

	}
}

func findElementInList(list *list.List, element int) bool {
	for e := list.Front(); e != nil; e = e.Next() {
		if e.Value == element {
			return true
		}
	}
	return false
}

func deleteElementFromList(list *list.List, element int) {
	for e := list.Front(); e != nil; e = e.Next() {
		if e.Value == element {
			list.Remove(e)
		}
	}
}

func workWithCustomer(operator int, clientsList *list.List, lock *sync.Mutex) {
	for true {
		lock.Lock()
		if clientsList.Len() > 0 {

			num := clientsList.Front().Value.(int)

			println("Operator #", operator, " working with customer #", num, ";")
			front := clientsList.Front()
			clientsList.Remove(front)

		}
		lock.Unlock()
		time.Sleep(2000 * time.Millisecond)
	}
}
