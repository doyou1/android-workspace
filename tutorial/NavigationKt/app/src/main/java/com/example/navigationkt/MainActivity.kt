package com.example.navigationkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_navi = findViewById<ImageView>(R.id.btn_navi)
        btn_navi.setOnClickListener {
            val layout_drawer = findViewById<DrawerLayout>(R.id.layout_drawer)
            layout_drawer.openDrawer(GravityCompat.START)   // START: left, END : right
        }

        val naviView = findViewById<NavigationView>(R.id.naviView)
        naviView.setNavigationItemSelectedListener(this)    // 네비게이션 메뉴 아이템 클릭 속성 부여

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {    // 네비게이션 메뉴 아이템 클릭시 수행
        when (item.itemId) {
            R.id.access -> Toast.makeText(applicationContext, "접근성", Toast.LENGTH_SHORT).show()
            R.id.email -> Toast.makeText(applicationContext, "E-mail", Toast.LENGTH_SHORT).show()
            R.id.message -> Toast.makeText(applicationContext, "메시지", Toast.LENGTH_SHORT).show()
        }

        val layout_drawer = findViewById<DrawerLayout>(R.id.layout_drawer)
        layout_drawer.closeDrawers()    // 네비게이션 뷰 닫기
        return false
    }

    override fun onBackPressed() {  // BackButton 누를 시 수행하는 메서드
        val layout_drawer = findViewById<DrawerLayout>(R.id.layout_drawer)
        if (layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawers()
        } else {
            super.onBackPressed()   // 일반 BackButton 기능 실행 (finish)
        }
    }
}