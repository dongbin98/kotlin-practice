package com.example.kotlin_practice

fun main() {
//    val user = User("염동빈")
//    println(user.age)
//    val kid = Kid("아이", 4, "male")

//    val person = Person("봉환", 26)
//    val dog = Dog("겨울", 3)
//
//    println(person.toString())  // class 값 자체가 나옴
//    println(dog.toString()) // toString()이 자동 구현됨, override 가능
//    println(dog.copy(age = 2).toString())  // age만 2로 변경된 객체를 얻을 수 있음

    val cat: Cat = BlueCat()
    val result = when(cat) {
        is BlueCat -> {"blue fur"}
        is RedCat -> {"red fur"}
        is GreenCat -> {"green fur"}
//      else -> {"mixed fur"}   // abstract class Cat()를 상속받은 경우, else로 들어갈 case가 없지만 컴파일러가 이를 알지 못함
    //                               sealed class Cat()의 경우 else가 redundant -> 컴파일러가 Cat이 어떤 자식클래스를 갖고있는지 알게됨
    }
    println(result)
}

// 기본적으로 상속에 대해 닫혀 있음 (open 키워드를 통해 가능)
// 주 생성자가 생략되어 있음
open class User(open val name: String, open var age: Int = 19)

class Kid constructor(override val name: String, override var age: Int): User(name, age) {
    var gender: String = "female"

    init {
        println("Initializing")
    }

    // 부 생성자 (Secondary Constructor)
    constructor(name: String, age: Int, gender: String): this(name, age) {
        this.gender = gender
        println("부 생성자 호출")
    }
}

class Person(
    val name: String,
    val age: Int,
)

data class Dog(
    // 1개 이상의 프로퍼티가 필요
    val name: String,
    val age: Int,
) //{
//    override fun toString(): String {
//        return "직접 구현 $name"
//    }
//}

//data class Corgi(
//    val pretty: Boolean = true
//) : Dog() --> 불가능

sealed class Cat
class BlueCat: Cat()
class RedCat: Cat()
class GreenCat: Cat()