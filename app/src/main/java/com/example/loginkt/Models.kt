package com.example.loginkt


data class User(var name:String, var email:String, var pass:String, var dept:String, var regNo:String, var year:Int)

data class Question(var name: String,var year: Int,var dept: String, var question:String,var subject: String,var solution: List<String> = emptyList(),var answerIndex:Int=-1 )