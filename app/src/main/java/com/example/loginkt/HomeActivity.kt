package com.example.loginkt


import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val fabR = findViewById<FloatingActionButton>(R.id.addQnFab)
        fabR.setOnClickListener{
            val intent = Intent(this, AskActivity::class.java)
            startActivity(intent)
        }
    }
}