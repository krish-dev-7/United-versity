package com.example.loginkt

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainAdapters(var questions: ArrayList<QuestionFB>,var context:Context) : RecyclerView.Adapter<MainAdapters.MyHolder>() {
    class MyHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val subNameRef = itemView.findViewById<TextView>(R.id.rvSub)
        private val qnRef = itemView.findViewById<TextView>(R.id.rvQn)
        private val answersRef = itemView.findViewById<RecyclerView>(R.id.rvAns)
        private val imgRef = itemView.findViewById<ImageView>(R.id.imageView)
        private val emtTxt = itemView.findViewById<TextView>(R.id.emptyTextView)
        private val myAnsRef = itemView.findViewById<EditText>(R.id.myAns)
        private val sendAnsRef = itemView.findViewById<ImageButton>(R.id.sendAns)

        fun binder(x:QuestionFB, context:Context){
            subNameRef.text=x.subject
            qnRef.text=x.question
            if(x.solution.isEmpty()){
                imgRef.visibility=View.VISIBLE
                emtTxt.visibility=View.VISIBLE
                answersRef.visibility=View.GONE
            }
            else{
                imgRef.visibility=View.GONE
                emtTxt.visibility=View.GONE
                answersRef.visibility=View.VISIBLE
            answersRef.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            answersRef.adapter = SubAdapter(x.solution, x.answerIndex)}
            sendAnsRef.setOnClickListener{
                addAnswers(myAnsRef.text.toString(),x).addOnSuccessListener {
                    Toast.makeText(context,"Answer Added!",Toast.LENGTH_LONG).show()
                    val intent = Intent(context,HomeActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapters.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_main_item,parent,false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MainAdapters.MyHolder, position: Int) {
        holder.binder(questions[position],context )
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}

class SubAdapter(private var answers :List<String>?, private var ansIndex:Int) : RecyclerView.Adapter<SubAdapter.MyHolder>(){
    class MyHolder(itemView: View,):RecyclerView.ViewHolder(itemView) {
        private var answerRef = itemView.findViewById<TextView>(R.id.rvRvAns)
        fun binder(x: String, i:Int,ansIndex: Int){
            if(i==ansIndex){
                answerRef.setTextColor(Color.parseColor("#1f7a1f"))
            }
            else{
                answerRef.setTextColor(Color.BLACK)
            }
            answerRef.text = x
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_sub_item,parent,false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: SubAdapter.MyHolder, position: Int) {

        holder.binder(answers?.get(position)?:"No Answer",position, ansIndex)

    }

    override fun getItemCount(): Int {
        return answers?.size?:0
    }

}