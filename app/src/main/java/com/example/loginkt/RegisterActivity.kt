package com.example.loginkt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth =  FirebaseAuth.getInstance()
        title = "Sign Up"
        val emailRef = findViewById<EditText>(R.id.emailTxtR)
        val nameRef = findViewById<EditText>(R.id.name)
        val regNoRef = findViewById<EditText>(R.id.regNo)
        val deptTglRef = findViewById<EditText>(R.id.dptToggle)
        val yrTglRef = findViewById<TextView>(R.id.yrToggle)
        val passRef = findViewById<TextView>(R.id.passwordTxtR)
        val regRef = findViewById<Button>(R.id.regBtn)
        regRef.setOnClickListener {
            emailG = emailRef.text.toString()
            passG = passRef.text.toString()
            auth.createUserWithEmailAndPassword(emailG, passG).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}