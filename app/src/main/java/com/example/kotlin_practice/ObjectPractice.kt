package com.example.kotlin_practice

import com.example.kotlin_practice.Book.Companion.NAME

fun main() {
    // 선언과 동시에 오직 한번만 생성됨
    Counter
    println(Counter.count)

    Counter.countUp()
    Counter.countUp()

    println(Counter.count)
    Counter.hello()

    println(NAME)
    Book.build()
}

// 클래스 상속 가능
object Counter: Hello() {
    // 생성자 사용 불가능
    init {
        println("Initializing")
    }
    var count = 0
    fun countUp() {
        count++
    }
}

open class Hello() {
    fun hello() = println("Hello")
}

class Book {
    companion object {
        const val NAME = "bonghwan"
        // builder pattern
        fun build() = Book()
    }
//    companion object {
//        // 한개만 사용 가능
//    }
}