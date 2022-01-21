package com.example.sharedkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {    // 액티비티 구동시 최초 1회 호출 (초기화)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: 저장된 데이터 로드
        loadData()  // EditText text값 setText
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        val et_hello = findViewById<EditText>(R.id.et_hello)
        et_hello.setText(pref.getString("name", ""))    // param1 key, param2 defaultValue
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()  // 수정모드

        val et_hello = findViewById<EditText>(R.id.et_hello)

        edit.putString("name", et_hello.text.toString())  // param1 key, param2 value
        edit.apply()    // 저장 완료
    }

    override fun onDestroy() {  // 액티비티 종료시 호출
        super.onDestroy()

        saveData()  // editText 저장
    }


}