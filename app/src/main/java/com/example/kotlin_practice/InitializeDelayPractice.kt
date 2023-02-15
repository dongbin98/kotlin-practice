package com.example.kotlin_practice

/* 초기화 지연 lateinit, lazy
-> 정의 : 변수를 선언 시 값을 지정하지 않고, 나중에 지정하는 방법
-> 목적 : 메모리 효율적 사용, null safe한 value 사용을 위함
 */

lateinit var text: String // 반드시 type 지정, var 에서만 사용 가능
// lateinit var age: Int -> primitive type은 not allowed
lateinit var age: Integer   // reference type으로 사용 가능

val test: Int by lazy { // 호출하는 시점에 초기화, val에서만 사용 가능, 내부 식은 lambda
    println("초기화 중")
    100
}

fun main() {
    println("main()")
    println("초기화 한 값 $test")
    println("두번째 호출 $test")
}