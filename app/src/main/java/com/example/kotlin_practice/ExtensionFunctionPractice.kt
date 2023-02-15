package com.example.kotlin_practice

fun main() {
    // 확장함수 (Extension Function)
    // 기존에 정의되어 있는 클래스에 함수를 추가하는 기능 -> Custom을 위해 상속을 하지 않아도 됨
    val test = Test()
    Test().hello()
    test.hi()
}

// 확장함수 정의
fun Test.hi() = println("하이")

class Test() {
    fun hello() = println("안녕")
    fun bye() = println("잘가")
}