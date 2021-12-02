package main

import "fmt"

func split(sum int) (x, y int) {
	x = sum * 4
	y = sum * 5
	return x, y
}

func main() {
	x, y := split(2)
	fmt.Println(x, y)

}
