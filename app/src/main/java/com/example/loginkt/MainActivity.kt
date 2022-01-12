package com.example.loginkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


lateinit var emailG :String
lateinit var passG :String
lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    private fun firebaseLogin(email:String, pass:String){
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        auth =  FirebaseAuth.getInstance()
        title = "Login"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailRef = findViewById<TextView>(R.id.emailTxt)
        val passRef = findViewById<TextView>(R.id.passwordTxt)
        val loginRef = findViewById<Button>(R.id.loginBtn)
        val sUpTBRef = findViewById<Button>(R.id.SupTB)
        sUpTBRef.setOnClickListener{
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginRef.setOnClickListener {
            emailG = emailRef.text.toString()
            passG = passRef.text.toString()
            firebaseLogin(emailG, passG)
        }

    }
}