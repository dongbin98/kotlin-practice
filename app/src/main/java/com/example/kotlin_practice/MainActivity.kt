package com.example.kotlin_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SAM ( Single Abstract Method, 단일 추상 메서드)
        val view = View(this)

        /* Java Style
        view.setOnClickListener(
            new View.OnClickListner() {
                @Override
                public void onClick(View view) {
                    //
                }
            }
        )
         */
        // 람다로 식을 대신 넘김
        // view.setOnClickListener ({ println("안녕") })
        view.setOnClickListener { println("안녕")}
    }
}