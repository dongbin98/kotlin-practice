package com.example.kotlin_practice

fun main() {
    /* Java Style
    for(int i = 1; i < 11; i++) {
        / .. /
    }
     */

    // 1 .. 10 == IntRange(1, 10) == 1 until 11
    for(i in 1 .. 10) {
        print(i)
        print(".")
    }
    println()

    for(i in 1 .. 10 step(2)) {
        print(i)
        print(".")
    }
    println()

    for(i in 10 downTo 1 step(2)) {
        print(i)
        print(".")
    }
    println()

    var c = 1
    while(c < 11) {
        print(c)
        print(".")
        c++
    }
}