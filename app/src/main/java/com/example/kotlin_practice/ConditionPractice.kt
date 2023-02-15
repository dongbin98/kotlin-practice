package com.example.kotlin_practice

fun main() {
    max(4, 7)
    isHoliday("금")
}

fun max(a: Int, b: Int) {
    /* Java Style
    if(a > b) {
        println(a)
    }
    else {
        println(b)
    }
     */
    val result = if (a > b) a else b
    println(result)
}

// 월 화 수 목 금 토 일
fun isHoliday(dayOfWeek: Any) {
    /* Java Style
    switch(dayOfWeek) {
        case "월"
        ...
    }
     */
//    when(dayOfWeek) {
//        "월",
//        "화",
//        "수",
//        "목",
//        "금" -> false
//        "토",
//        "일" -> true
//    }
    // Expression을 식으로 사용할 경우엔 else 필수
//    val result = when(dayOfWeek) {
//        "토",
//        "일" -> true
//        else -> false
//    }
    val result = when(val day = dayOfWeek) {
        "토",
        "일" -> if(day == "토") "Good" else "Very Good"
        // use range of Number
        in 2 .. 4 -> {}
        // use list
        in listOf("월", "화") -> {}
        else -> false
    }
    println(result)
}