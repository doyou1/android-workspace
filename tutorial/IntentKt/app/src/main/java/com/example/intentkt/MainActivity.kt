package com.example.intentkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_a).setOnClickListener {
            // var : 변수, 변경가능
            // val : Java final, js const, 변경불가
            val intent = Intent(this, SubActivity::class.java)  // 다음 화면으로 이동하기 위한 intent 객체 생성
            intent.putExtra("msg", findViewById<TextView>(R.id.tv_sendMsg).text.toString()) // 키: msg, 값: TextView의 text
            
            startActivity(intent)   // 해댕 Activity로 이동
            finish()    // 자기 자신 액티비티 종료
        }
    }
}