package com.example.quizapp

class QuestionData{
    var question = ""
    var userAnswer = "true"
    var answer = "true"

    constructor(question: String, userAnswer: String, answer: String){
        this.question = question
        this.userAnswer = userAnswer
        this.answer = answer
    }
}