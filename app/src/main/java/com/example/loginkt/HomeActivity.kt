package com.example.loginkt


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.*

class HomeActivity:AppCompatActivity() {
    var questions = ArrayList<QuestionFB>()
    var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var myAdapter : MainAdapters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val fabR = findViewById<FloatingActionButton>(R.id.addQnFab)
        val mainRvRef = findViewById<RecyclerView>(R.id.mainRv)
        myAdapter = MainAdapters(questions,this)
        mainRvRef.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        mainRvRef.adapter = myAdapter
        eventChangeListener()
        fabR.setOnClickListener{
            val intent = Intent(this, AskActivity::class.java)
            startActivity(intent)
        }
    }
    private fun eventChangeListener(){
        db.collection(signedUser.year.toString()).addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(applicationContext, error.localizedMessage, Toast.LENGTH_LONG).show()
                Log.e("EventChangeListener", error.toString())
                return@addSnapshotListener
            }
            else{
                var index =0
                for(dc:DocumentChange in value?.documentChanges!!){
                    if(dc.type==DocumentChange.Type.ADDED){
                        questions.add(dc.document.toObject(QuestionFB::class.java))
                    }
                }
            }
            myAdapter.notifyDataSetChanged()
        }
    }
}

// follow https://youtu.be/Ly0xwWlUpVM --> 14:54