package com.example.kotlin_practice

fun main() {
    println(check("Hi"))
    println(check(3))
    println(check(false))

    cast("안녕")
    cast(3)

    println(smartCast("Hi"))
    println(smartCast(4))
    println(smartCast(true))
}

fun check(a: Any): String {
    // Java에서는 instanceOf, Kotlin에서는 is
    return when (a) {
        is String -> {
            "문자열"
        }
        is Int -> {
            "숫자"
        }
        else -> {
            "모름"
        }
    }
}

fun cast(a: Any) {
    val result = a as? String   // String으로 변환 불가시 null로 만들어줌
    val result2 = a as? String ?: "실패"   // String으로 변환 불가시 값 지정 (Elvis Operator)
    println(result)
    println(result2)
}

fun smartCast(a: Any): Int {
    return if(a is String) {
        // a가 String으로 확인 되었기때문에 String으로 확장함수 사용 가능
        a.length
    } else if (a is Int) {
        a.dec()
    } else {
        -1
    }
}