package com.example.kotlin_practice

fun main() {
    // List in Java -> Mutable

    val list = mutableListOf(1, 2, 3, 4, 5) // Mutable
    list.add(6)
    list.addAll(listOf(7, 8, 9))

    val list2 = listOf(1, 2, 3, 4)  // Immutable

    // 여러 타입을 담을 수 있음
    val diverseList = listOf(1, "Hi", 3.14, true)

    // 확장 함수 예시
    println(list2.map { it * 20 }.joinToString("/"))

    println(list.joinToString(","))
    println(diverseList.joinToString(","))

    val map = mutableMapOf((1 to "Hi"), (2 to "Bye"))   // Mutable
    map.put(3, "Yes")
    map[4] = "No"
    val map2 = mapOf((1 to "Hi"), (2 to "Bye"))  // Immutable

}