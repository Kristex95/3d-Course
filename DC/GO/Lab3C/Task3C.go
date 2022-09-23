package main

import (
	"math/rand"
	"sync"
	"time"
)

func main() {
	var ingridients = []string{"tobacco", "paper", "match"}
	var table [2]string
	var tableIsEmpty = true
	var wg sync.WaitGroup

	for {
		wg.Add(1)
		medium := func() {
			defer wg.Done()
			i1 := rand.Intn(3)
			i2 := rand.Intn(3)
			for i1 == i2 {
				i2 = rand.Intn(3)
			}
			println(ingridients[i1])
			println(ingridients[i2])
			table[0] = ingridients[i1]
			table[1] = ingridients[i2]
			tableIsEmpty = false
		}
		go medium()
		wg.Wait()
		wg.Add(3)
		smoker := func(elem string) {
			defer wg.Done()
			var found = false
			if !tableIsEmpty {
				for i := range table {
					if table[i] == elem {
						found = true
						break
					}
				}
				if !found {
					tableIsEmpty = true
					for j := range table {
						table[j] = " "
					}
					println("added " + elem)
					println("Smoking...💨💨💨")
					time.Sleep(2 * time.Second)
				}
			}
		}
		go smoker("tobacco")
		go smoker("paper")
		go smoker("match")
		wg.Wait()
	}
}
