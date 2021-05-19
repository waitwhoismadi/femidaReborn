package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*
import kotlinx.android.synthetic.main.activity_main.*

class CheatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val answer = intent.getBooleanExtra("answer", true)

        show_answer_button.setOnClickListener {
            if (!answer){
                answer_text_view.text = "False"
            } else{
                answer_text_view.text = "True"
            }
        }

        back.setOnClickListener {
           finish()
        }
    }
}