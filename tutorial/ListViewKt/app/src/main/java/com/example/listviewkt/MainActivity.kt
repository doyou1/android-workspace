package com.example.listviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var UserList = arrayListOf<User>(
        User(R.drawable.android, "아키추", "30", "환영합니다"),
        User(R.drawable.ic_launcher_background, "자바", "30", "어렵죠"),
        User(R.drawable.android, "자바스크립트", "25", "어려운데 재밌구요"),
        User(R.drawable.ic_launcher_background, "코틀린", "5", "신선하네요"),
        User(R.drawable.android, "아키추", "30", "환영합니다"),
        User(R.drawable.ic_launcher_background, "자바", "30", "어렵죠"),
        User(R.drawable.android, "자바스크립트", "25", "어려운데 재밌구요"),
        User(R.drawable.ic_launcher_background, "코틀린", "5", "신선하네요"),
        User(R.drawable.android, "아키추", "30", "환영합니다"),
        User(R.drawable.ic_launcher_background, "자바", "30", "어렵죠"),
        User(R.drawable.android, "자바스크립트", "25", "어려운데 재밌구요"),
        User(R.drawable.ic_launcher_background, "코틀린", "5", "신선하네요"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {    // Activity tlf
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val items = arrayOf("사과", "바나나", "배", "딸기")
//        // context란 특정 액티비티의 모든 정보를 담고 있다.
//        findViewById<ListView>(R.id.listView).adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        val listView = findViewById<ListView>(R.id.listView)
        val adapter = UserAdapter(this, UserList)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectedItem.name, Toast.LENGTH_SHORT).show()
        }

    }
}