package com.example.loginkt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity(){
    private fun signUp(user:User){
        auth.createUserWithEmailAndPassword(user.email, user.pass).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.v("RegisterActivity", user.name)
                    adUserDB(user)
                        .addOnSuccessListener { Toast.makeText(this, "user created!", Toast.LENGTH_SHORT).show() }
                        .addOnFailureListener{exception -> Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()}
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth =  FirebaseAuth.getInstance()
        val emailRef = findViewById<EditText>(R.id.emailTxtR)
        val nameRef = findViewById<EditText>(R.id.name)
        val regNoRef = findViewById<EditText>(R.id.regNo)
        val deptTglRef = findViewById<MaterialButtonToggleGroup>(R.id.dptToggle)
        val yrTglRef = findViewById<MaterialButtonToggleGroup>(R.id.yrToggle)
        val passRef = findViewById<EditText>(R.id.passwordTxtR)
        val regRef = findViewById<Button>(R.id.regBtn)
        var dpt = "cse"
        var yr  = 2
        deptTglRef.addOnButtonCheckedListener{ _, checkedId, isChecked ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.cse -> dpt = "cse"
                        R.id.ece -> dpt = "ece"
                        R.id.eee -> dpt = "eee"
                        R.id.mech ->dpt = "mech"
                    }
                }
        }
        yrTglRef.addOnButtonCheckedListener{ _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.yr1 -> yr=1
                    R.id.yr2 -> yr=2
                    R.id.yr3 -> yr=3
                    R.id.yr4 -> yr=4
                }
            }
        }


        regRef.setOnClickListener {
            emailG = emailRef.text.toString()
            passG = passRef.text.toString()
            signUp(User( name = nameRef.text.toString(),
                email=emailRef.text.toString(),
                pass=passRef.text.toString(),
                regNo=regNoRef.text.toString(),
                dept = dpt,
                year = yr))
        }
    }
}