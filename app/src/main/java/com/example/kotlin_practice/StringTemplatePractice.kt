package com.example.kotlin_practice

fun main() {
    val a = 10
    val name = "동빈"
    val isHigh = true

    /* Java Style
    println(a.toString() + name + isHigh.toString())
    String.format("%s %d", name, a)
     */

    // 브라켓을 넣으면 함수를 사용할 수 있음
    println("$a ${name.length} $isHigh")
}