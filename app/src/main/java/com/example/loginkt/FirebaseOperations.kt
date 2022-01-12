package com.example.loginkt

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

public fun adUserDB(user:User): Task<Void> {

    val docRef = FirebaseFirestore.getInstance().collection("User").document(user.email)
    Log.v("FireOp", user.name)
    val data = hashMapOf("name" to user.name,"dept" to user.dept,"year" to user.year,"pass" to user.pass,"regNo" to user.regNo)
    return docRef.set(
            data, SetOptions.merge()
    )
}