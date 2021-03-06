package com.example.listviewkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter(val context: Context, val UserList: ArrayList<User>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_user, null)

        val profile = view.findViewById<ImageView>(R.id.iv_profile)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val greet = view.findViewById<TextView>(R.id.tv_greet)
        val age = view.findViewById<TextView>(R.id.tv_age)

        val user = UserList[p0]
        profile.setImageResource(user.profile)
        name.text = user.name
        greet.text = user.greet
        age.text = user.age

        return view
    }

    override fun getItem(p0: Int): Any {

        return UserList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return UserList.size
    }

}