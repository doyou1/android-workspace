package com.example.imageviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var toggleFlag:Boolean = false
    val toggle = mapOf<Boolean, Int>(false to R.drawable.android, true to R.drawable.android_another)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_toast).setOnClickListener {
            toggleFlag = !toggleFlag
            val selectedImage = toggle[toggleFlag]?.toInt()
            if (selectedImage != null) {
                findViewById<ImageView>(R.id.iv_profile).setImageResource(selectedImage)
            }   // ImageView에 새로운 Image 설정
            Toast.makeText(this@MainActivity, "버튼 클릭, Toast Message Print $toggleFlag", Toast.LENGTH_SHORT).show()
            }
    }
}