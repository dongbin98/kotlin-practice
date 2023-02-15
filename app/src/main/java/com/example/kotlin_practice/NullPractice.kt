package com.example.kotlin_practice

fun main() {
    // Java <- Complie 단계에서 NPE 발견 불가능
    var name: String = "동빈"
    var number: Int = 10

    var nickName: String? = "오월이"    // ? <- nullable
    var secondNumber: Int? = null

//    val result = if(nickName == null) {
//        "No Value"
//    } else {
//        nickName
//    }
    // Elvis operator 위의 코드를 대체
    val result = nickName?: "값이 없음"
    val size = nickName?.length // null인 경우 실행하지 않고 끝냄, null이 아닌경우 length를 구함
                                // !! <- nullable로 선언했으나 사용 시 반드시 Non-null이어야 할 경우 !!로 assert
    println(size)
}