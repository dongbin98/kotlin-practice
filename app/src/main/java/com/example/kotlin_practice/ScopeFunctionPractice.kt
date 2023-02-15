package com.example.kotlin_practice

fun main() {
    // let, run, apply, also, with
    val user: Profile? = Profile("염동빈", 26, true)

    /* 1. let ---> null check, local variable 명시적 표현 시
    수신객체.let { it ->  (명시적으로 it 대신 local variable을 지정해서 가능함)
        ..
        마지막 줄 (return)
    }
     */
    val age = user?.let {
        it.age
    }
    println(age)

    /* 2. run ---> 객체 초기화 하고 리턴 값이 있을 때
    수신객체.run { this -> (this 생략 가능, local variable 사용 불가)
        ..
        마지막 줄 (return)
    }
     */
    val kid = Profile("아기", 6, false)
    val kidAge = kid.run {
        age
    }
    println(kidAge)

    /* 3. apply ---> 객체 초기화
    수신객체.apply { this -> (this 생략 가능, local variable 사용 불가)
        ..
        return 값은 수신객체 (자기자신)
    }
     */
    val female = Profile("여자애", 20, false, true)
    val femaleValue = female.apply {
        hasGlasses = false
    }
    println(femaleValue.hasGlasses)

    /* 4. also ---> 수신 객체의 내용을 확인할 때(로그 찍을 때) 사용 권장, 초기화에 대한 작업을 권장하진 않음
    수산객체.also ( it -> (명시적으로 it 대신 local variable을 지정해서 가능함)
        ..
        return 값은 수신객체
    }
     */
    val male = Profile("남자애", 18, true, true)
    val maleValue = male.also {
        println(it.name)
    }

    /* 5. with ---> 객체 초기화, 람다 리턴값이 필요없을 때
    with(수신객체) {    // 확장 함수는 아님, this 생략 가능, local variable 사용 불가
        ..
        마지막 줄 (return)
     */
    val result = with(male) {
        hasGlasses = false
        true
    }
    println(result)
}

class Profile(
    val name: String,
    val age: Int,
    val gender: Boolean,    // true : male, false : female
    var hasGlasses: Boolean = true,
)