package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var score = 0
    private var questionIndex = 0
    private var answerIndex = 0
    private var questionStrings = mutableListOf(R.string.question1, R.string.question2, R.string.question3, R.string.question4,
            R.string.question5, R.string.question6)
    private var answers = mutableListOf(true, false, false, false, true, false)
    private var currentAnswer = true

    var database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quiz()
    }

    private fun quiz(){
        start.setOnClickListener {
            onStartButton()
        }

        trueButton.setOnClickListener {
            onTrueButton()
        }

        falseButton.setOnClickListener {
            onFalseButton()
        }

        useHint.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra("answer", answers[answerIndex])
            startActivity(intent)
        }
    }

    private fun onFalseButton(){
        currentAnswer = false
        if (questionIndex < 5){
            score += if (!answers[answerIndex]){
                1
            } else{
                0
            }
            updateQuestionAndAnswer()
        } else{
            questiontv.text = "Your score: " + score.toString()
            start.visibility = View.VISIBLE
            trueButton.visibility = View.INVISIBLE
            falseButton.visibility = View.INVISIBLE
            useHint.visibility = View.INVISIBLE
        }
    }

    private fun onTrueButton(){
        currentAnswer = true
        if (questionIndex < 5){
            score += if (answers[answerIndex]){
                1
            } else{
                0
            }
            updateQuestionAndAnswer()
        } else{
            questiontv.text = "Your score: " + score.toString()
            start.visibility = View.VISIBLE
            trueButton.visibility = View.INVISIBLE
            falseButton.visibility = View.INVISIBLE
            useHint.visibility = View.INVISIBLE
        }
    }

    private fun onStartButton(){
        score = 0
        questionIndex = 0
        answerIndex = 0
        trueButton.visibility = View.VISIBLE
        falseButton.visibility = View.VISIBLE
        questiontv.visibility = View.VISIBLE
        questiontv.text = getString(questionStrings[0])
        start.visibility = View.INVISIBLE
        useHint.visibility = View.VISIBLE
    }

    private fun updateQuestionAndAnswer(){
        var question = getString(questionStrings[questionIndex])
        var answer = currentAnswer.toString()
        var correctAnswer = answers[answerIndex].toString()
        database.child((questionIndex + 1).toString()).setValue(QuestionData(question, answer, correctAnswer))
        score = score
        questionIndex += 1
        answerIndex += 1
        questiontv.text = getString(questionStrings[questionIndex])
        quiz()
    }

}