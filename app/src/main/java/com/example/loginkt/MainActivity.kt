package com.example.loginkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


lateinit var emailG :String
lateinit var passG :String


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var emailRef = findViewById<TextView>(R.id.emailTxt)
        var passRef = findViewById<TextView>(R.id.passwordTxt)
        var loginRef = findViewById<Button>(R.id.loginBtn)
        loginRef.setOnClickListener {
            emailG = emailRef.text.toString()
            passG = passRef.text.toString()
            if(emailG=="1234" && passG=="1234"){
                val intent =Intent(this, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Logged in!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext,"Wrong Password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}