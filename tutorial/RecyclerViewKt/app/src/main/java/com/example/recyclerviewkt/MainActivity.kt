package com.example.recyclerviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileList = arrayListOf<Profiles>(
            Profiles(R.drawable.man1, "JH", "안드로이드 개발자", 29),
            Profiles(R.drawable.woman1, "아키", "아이폰 개발자", 28),
            Profiles(R.drawable.man2, "김재형", "플러터 개발자", 27),
            Profiles(R.drawable.woman2, "홍재형", "리액트 개발자", 26),
            Profiles(R.drawable.man1, "JH", "안드로이드 개발자", 25),
            Profiles(R.drawable.woman1, "아키", "아이폰 개발자", 32),
            Profiles(R.drawable.man2, "김재형", "플러터 개발자", 20),
            Profiles(R.drawable.woman2, "홍재형", "리액트 개발자", 35),
        )
        val rv_profile = findViewById<RecyclerView>(R.id.rv_profile)
        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter = ProfileAdapter(profileList)
    }
}