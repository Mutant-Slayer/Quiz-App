package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView
    private lateinit var checkButton: Button

    private lateinit var textViewOptionOne: TextView
    private lateinit var textViewOptionTwo: TextView
    private lateinit var textViewOptionThree: TextView
    private lateinit var textViewOptionFour: TextView

    private var questionCounter = 0
    private lateinit var questionsList: MutableList<Question>
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false

    private lateinit var userName: String
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        initUi()
        setUpListeners()

        questionsList = Constants.getQuestions()
        showNextQuestion()

        if (intent.hasExtra(Constants.USER_NAME)) {
            userName = intent.getStringExtra(Constants.USER_NAME).toString()
        }
    }

    private fun setUpListeners() {
        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)
    }

    private fun initUi() {
        progressBar = findViewById(R.id.pbQuestion)
        textViewProgress = findViewById(R.id.tvProgress)
        textViewQuestion = findViewById(R.id.tvQuestion)
        flagImage = findViewById(R.id.ivFlag)
        checkButton = findViewById(R.id.btnCheck)

        textViewOptionOne = findViewById(R.id.tvOptionOne)
        textViewOptionTwo = findViewById(R.id.tvOptionTwo)
        textViewOptionThree = findViewById(R.id.tvOptionThree)
        textViewOptionFour = findViewById(R.id.tvOptionFour)
        checkButton = findViewById(R.id.btnCheck)
    }

    private fun showNextQuestion() {

        if (questionCounter < questionsList.size) {
            checkButton.text = getString(R.string.check)
            currentQuestion = questionsList[questionCounter]

            resetOptions()
            val question = questionsList[questionCounter]

            progressBar.progress = questionCounter
            flagImage.setImageResource(question.image)
            textViewProgress.text = "$questionCounter" + "/" + progressBar.max
            textViewQuestion.text = question.question

            textViewOptionOne.text = question.optionOne
            textViewOptionTwo.text = question.optionTwo
            textViewOptionThree.text = question.optionThree
            textViewOptionFour.text = question.optionFour
        } else {
            checkButton.text = getString(R.string.finish)
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, userName)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                startActivity(it)
                finish()
            }
        }

        questionCounter++
        answered = false
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(0, textViewOptionOne)
        options.add(1, textViewOptionTwo)
        options.add(2, textViewOptionThree)
        options.add(3, textViewOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

        selectedAnswer = selectedOptionNumber
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.tvOptionOne -> {
                selectedOption(textViewOptionOne, 1)
            }

            R.id.tvOptionTwo -> {
                selectedOption(textViewOptionTwo, 2)
            }

            R.id.tvOptionThree -> {
                selectedOption(textViewOptionThree, 3)
            }

            R.id.tvOptionFour -> {
                selectedOption(textViewOptionFour, 4)
            }

            R.id.btnCheck -> {
                if (answered.not()) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun checkAnswer() {
        answered = true

        if (selectedAnswer == currentQuestion.correctAnswer) {
            // Correct answer
            score++
            highlightAnswer(selectedAnswer)
        } else {
            // Wrong answer
            highlightWrongAnswer(selectedAnswer)
        }
        checkButton.text = getString(R.string.next)
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer

        highlightAnswer(selectedAnswer)
    }

    private fun highlightAnswer(answer: Int) {
        when (answer) {
            1 -> {
                textViewOptionOne.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
            }

            2 -> {
                textViewOptionTwo.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
            }

            3 -> {
                textViewOptionThree.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
            }

            4 -> {
                textViewOptionFour.background =
                    ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
            }
        }
    }

    private fun highlightWrongAnswer(answer: Int) {
        when (answer) {
            1 -> {
                textViewOptionOne.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
            }

            2 -> {
                textViewOptionTwo.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
            }

            3 -> {
                textViewOptionThree.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
            }

            4 -> {
                textViewOptionFour.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
            }
        }
    }
}