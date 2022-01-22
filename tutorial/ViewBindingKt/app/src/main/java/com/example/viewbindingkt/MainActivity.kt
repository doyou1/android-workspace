package com.example.viewbindingkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.viewbindingkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     *  build.gradle에서 viewBinding: true 설정 
     *  & 
     *  현재 Activity가 activity_main과 연결되기에
     *  ActivityMainBinding = "Activity"(activity) + "Main"(main) + "Binding"의 Class Name으로 자동 생성
     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)    // 위 주석 코드와 동일한 의미

        // binding use
        binding.tvMessage.text = "ViewBinding을 이용한 setText"

        binding.btn.setOnClickListener {
            Toast.makeText(this, "ViewBinding을 이용한 onClick Event", Toast.LENGTH_SHORT).show()
        }

    }
}