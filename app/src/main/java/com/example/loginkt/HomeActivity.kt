package com.example.loginkt


import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var txtRef = findViewById<TextView>(R.id.greetTxt)
        txtRef.setText("Welcome ${emailG}")
    }
}