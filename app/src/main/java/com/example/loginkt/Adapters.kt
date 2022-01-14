package com.example.loginkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainAdapters(var questions: ArrayList<QuestionFB>,var context:Context) : RecyclerView.Adapter<MainAdapters.MyHolder>() {
    class MyHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val subNameRef = itemView.findViewById<TextView>(R.id.rvSub)
        private val qnRef = itemView.findViewById<TextView>(R.id.rvQn)
        private val answersRef = itemView.findViewById<RecyclerView>(R.id.rvAns)
        fun binder(x:QuestionFB, context:Context){
            subNameRef.text=x.subject
            qnRef.text=x.question
            answersRef.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            answersRef.adapter = SubAdapter(x.solution)

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

class SubAdapter(var answers :List<String>?) : RecyclerView.Adapter<SubAdapter.MyHolder>(){
    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val answerRef = itemView.findViewById<TextView>(R.id.rvRvAns)
        fun binder(x: String){
            answerRef.text = x
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_sub_item,parent,false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: SubAdapter.MyHolder, position: Int) {
        holder.binder(answers?.get(position)?:"No Answer")

    }

    override fun getItemCount(): Int {
        return answers?.size?:0
    }

}