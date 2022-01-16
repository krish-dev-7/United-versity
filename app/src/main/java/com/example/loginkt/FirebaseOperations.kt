package com.example.loginkt

import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

lateinit var signedUser: User
fun addUserDB(user:User): Task<Void> {

    val docRef = FirebaseFirestore.getInstance().collection("User").document(user.email)
    Log.v("FireOp", user.name)
    val data = hashMapOf("name" to user.name,"dept" to user.dept,"year" to user.year,"pass" to user.pass,"regNo" to user.regNo)
    return docRef.set(
            data, SetOptions.merge()
    )
}

fun getUser(): Task<DocumentSnapshot> {
    var regNo:String
    var name:String
    var year:Int
    var dept:String
    val userRef = FirebaseFirestore.getInstance().collection("User").document(emailG)
    return userRef.get().addOnSuccessListener { document ->
        regNo = document.data?.get("regNo").toString()
        name = document.data?.get("name").toString()
        year = Integer.parseInt(document.data?.get("year").toString())
        dept = document.data?.get("dept").toString()
        Log.v("getUser()", year.toString())
        signedUser = User(name, emailG, passG,dept,regNo,year)
    }.addOnFailureListener{
        Log.v("GetUser",it.localizedMessage?:"error")
    }
}

fun addQuestion(question : Question): Task<DocumentReference> {
    val db = FirebaseFirestore.getInstance()
    val data = hashMapOf("dept" to question.dept,"question" to question.question, "answerIndex" to question.answerIndex,"subject" to question.subject, "name" to question.name, "email" to question.email)
    //added 2-->randomDocId-->question
    return db.collection(question.year.toString()).add(data).addOnSuccessListener { dcc ->
        val docD= hashMapOf("docId" to dcc.id)
        db.collection(question.year.toString()).document(dcc.id).set(docD, SetOptions.merge())
        val userQn = hashMapOf("question" to question.question, "answerIndex" to question.answerIndex,"subject" to question.subject)
        //added user-->userEmail-->askedQn-->question
        db.collection("User").document(signedUser.email).collection("askedQns").add(userQn).addOnSuccessListener {
            val docDA= hashMapOf("docId" to it.id)
            val docDU= hashMapOf("userDocId" to it.id)
            db.collection("User").document(signedUser.email).collection("askedQns").document(it.id).set(docDA, SetOptions.merge())
            db.collection(question.year.toString()).document(dcc.id).set(docDU, SetOptions.merge())
        }
    }
}

fun addAnswers(answer : String,question: QuestionFB): Task<Void> {
    val db = FirebaseFirestore.getInstance()
    val data = hashMapOf("solution" to FieldValue.arrayUnion(answer))
    return question.docId?.let {
        db.collection(signedUser.year.toString()).document(it).set(data, SetOptions.merge()).addOnSuccessListener {
            question.email?.let { it1 ->
                question.userDocId?.let { it2 ->
                    db.collection("User").document(it1).collection("askedQns").document(it2).set(data, SetOptions.merge()) }
            }
        }
    }!!
}