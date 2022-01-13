package com.example.loginkt

import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

lateinit var signedUser: User
fun addUserDB(user:User): Task<Void> {

    val docRef = FirebaseFirestore.getInstance().collection("User").document(user.email)
    Log.v("FireOp", user.name)
    val data = hashMapOf("name" to user.name,"dept" to user.dept,"year" to user.year,"pass" to user.pass,"regNo" to user.regNo)
    return docRef.set(
            data, SetOptions.merge()
    )
}

fun getUser():Boolean{
    var flag = true
    var regNo:String
    var name:String
    var year:Int
    var dept:String
    val userRef = FirebaseFirestore.getInstance().collection("User").document(emailG)
    userRef.get().addOnSuccessListener { document ->
        regNo = document.data?.get("regNo").toString()
        name = document.data?.get("name").toString()
        year = Integer.parseInt(document.data?.get("year").toString())
        dept = document.data?.get("dept").toString()
        signedUser = User(name, emailG, passG,dept,regNo,year)
        flag = true
    }.addOnFailureListener{
        flag=false
        Log.v("GetUser",it.localizedMessage?:"error")
    }
    return flag
}

fun addQuestion(question : Question): Task<Void> {
    val db = FirebaseFirestore.getInstance()
    val data = hashMapOf("question" to question.question, "answerIndex" to question.answerIndex,"subject" to question.subject, "name" to question.name)
    return db.collection(question.year.toString()).document(question.dept).set(data, SetOptions.merge())
}