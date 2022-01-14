package com.example.loginkt


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity:AppCompatActivity() {
    var questions = ArrayList<QuestionFB>()
    init {
        for(i:Int in 1..10){
            questions.add(QuestionFB(-1,"cse $i","krishna $i","$i. multiply $i * ${i*i}","maths $i", listOf("s","Sas")))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val fabR = findViewById<FloatingActionButton>(R.id.addQnFab)
        val mainRvRef = findViewById<RecyclerView>(R.id.mainRv)
        Log.v("Question", questions[0].solution[0])
        mainRvRef.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mainRvRef.adapter = MainAdapters(questions,this)
        fabR.setOnClickListener{
            val intent = Intent(this, AskActivity::class.java)
            startActivity(intent)
        }
    }
}

// follow https://youtu.be/Ly0xwWlUpVM --> 14:54