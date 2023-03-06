package com.example.kotlin_practice

import android.graphics.Rect

// Extension function
fun String.withBar() = this.withPostfix("Bar")
private fun String.withPostfix(postFix: String) = "$this$postFix"

fun main() {
    // val = value (Immutable)
    // var = variable (Mutable)

//    val a: Int = 3
//    var b: Int = 10
    // a = 4 (Can't reassigned)

//    println("result = ${test(5, c = 10)}")
//    test2(name = "염동빈", id = "4", nickName = "Bin

    val rA = Rectangle(10, 10)
    val rB = Rectangle(2, 10)
    println("${rA > rB} ${rA >= rB} ${rA <= rB} ${rA < rB}}")
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

class Rectangle(val width: Int, val height: Int)

// operator overloading
operator fun Rectangle.compareTo(other: Rectangle): Int {
    val myDimension = this.width * this.height
    val otherDimension = other.width * other.height
    return myDimension - otherDimension
}