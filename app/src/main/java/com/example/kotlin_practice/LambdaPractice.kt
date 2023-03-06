package com.example.kotlin_practice

fun main() {
    /*
    람다함수의 특징
    1. 익명함수 -> 함수의 이름이 없음
    2. 하나의 변수처럼 사용 가능 (함수의 arg, 함수의 return 값이 가능)
    3. 한번만 사용되고, 재사용되지 않는 함수
     */
    val a = fun() { println("No arg, No return, No name") }
    // Type :: (Args) -> Return)
    val b: (Int) -> Int = {
        // 코드의 마지막 줄이 return 값이 됨
        it * 10
    }
    val d = { i: Int, j: Int -> i * j}
    // _를 통해 사용 안하는 것 생략 가능
    val f: (Int, String, Boolean) -> String = { _, b, _ -> b}


    // 코드 조각이 출력
    println(b)
    // 람다식 실행
    println(b(10))
    println(d(5, 7))

    hello(10, b)

    val humanList = listOf<Human>(
        Human("동빈", 26),
        Human("봉환", 17),
        Human("성현", 28),
        Human("한문", 26),
    )

    printAdults(humanList)

    doSomething { println("do something") }
}

fun hello(a: Int, b: (Int) -> Int): (Int) -> Int {
    println(a)
    println(b(5))
    return b
}

class Human(val name: String, age: Int) {
    val adult = age > 19
}

fun printAdults(human: List<Human>) {
    human.filter { human -> human.adult}.forEach {
        println("Name = ${it.name}")
    }

    human.filter(Human::adult).forEach {
        println("Name = ${it.name}")
    }
}

inline fun doSomething(body: () -> Unit) {
    println("onPreExecute()")
    body()
    println("onPostExecute()")
}