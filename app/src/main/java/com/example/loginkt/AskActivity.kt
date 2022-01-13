package com.example.loginkt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        val askBtn = findViewById<Button>(R.id.askBtn)
        askBtn.setOnClickListener{
            addQuestion(createQuestionObj()).addOnSuccessListener {
                Toast.makeText(this, "Question Added successfully!",Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, it.localizedMessage,Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun createQuestionObj(): Question {
        val qnRef = findViewById<EditText>(R.id.questionBy)
        val subRef = findViewById<EditText>(R.id.subjectName)
        return Question(signedUser.name, signedUser.year, signedUser.dept,qnRef.text.toString(),subRef.text.toString())
    }
}