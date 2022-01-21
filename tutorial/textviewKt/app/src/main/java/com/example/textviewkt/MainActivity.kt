package com.example.textviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {    // 앱이 최초 실행됐을 때 실행
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // xml 화면 뷰 연결
        var tv_title : TextView = findViewById(R.id.tv_title)
        tv_title.text = "Hello World!!!!!"  // 텍스트뷰의 값 변경
    }
}