package com.example.kotlin_practice

fun main() {
    // val = value (Immutable)
    // var = variable (Mutable)

    val a: Int = 3
    var b: Int = 10
    // a = 4 (Can't reassigned)

    println("result = ${test(5, c = 10)}")
    test2(name = "염동빈", id = "4", nickName = "Bin")
}

// function
fun test(a: Int, b: Int = 3, c:Int = 4): Int { // Unit in kotlin = void in Java (return type)
    // b, c overloading
    println("call test()")
    println(a+b+c)
    return a+b+c
}

// Single Expression
fun test2(name: String, nickName: String, id: String,) = println(name + nickName + id)
fun times(a: Int, b: Int) = a * b