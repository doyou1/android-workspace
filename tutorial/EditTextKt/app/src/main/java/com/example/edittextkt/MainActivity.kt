package com.example.edittextkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_getText: Button = findViewById(R.id.btn_getText)
        btn_getText.setOnClickListener {    // EditText에 입력돼있는 값을 가져와 TextView에 뿌려준다
            var result = findViewById<EditText>(R.id.et_id).text.toString()
            findViewById<TextView>(R.id.tv_result).text = result
        }
    }
}