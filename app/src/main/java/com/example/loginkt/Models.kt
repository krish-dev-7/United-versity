package com.example.loginkt


data class User(var name:String, var email:String, var pass:String, var dept:String, var regNo:String, var year:Int)

data class Question(var name: String,
                    var email: String,
                    var year: Int,
                    var dept: String,
                    var question: String,
                    var subject: String,
                    var solution: List<String> = emptyList(),
                    var answerIndex: Int = -1)
data class QuestionFB(var answerIndex: Int = -1,
                      var dept: String? = null,
                      var name: String? = null,
                      var email: String? = null,
                      var question: String? = null,
                      var subject: String? = null,
                      var solution: List<String> = emptyList(),
                      var docId: String? = null,
                      var userDocId: String? = null)