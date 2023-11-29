package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val SCORE: String = "correct_answers"

    fun getQuestions(): MutableList<Question> {
        val questions = mutableListOf<Question>()

        val que1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questions.add(que1)

        val que2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.bangladesh,
            "Angola",
            "Austria",
            "Bangladesh",
            "Armenia",
            3
        )
        questions.add(que2)

        val que3 = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.saudi_arabia,
            "Bahamas",
            "Saudi Arabia",
            "Barbados",
            "Belize",
            2
        )
        questions.add(que3)

        val ques4 = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.zimbabwe,
            "Bahamas",
            "Zimbabwe",
            "India",
            "Zambia",
            2
        )
        questions.add(ques4)

        val que5 = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.brazil,
            "Brazil",
            "Belarus",
            "Belize",
            "Dominica",
            1
        )
        questions.add(que5)

        val que6 = Question(
            6,
            "What country does this flag belong to?",
            R.drawable.canada,
            "Bosnia",
            "Canada",
            "Bangladesh",
            "SriLanka",
            2
        )

        questions.add(que6)

        val que7 = Question(
            7,
            "What country does this flag belong to?",
            R.drawable.cyprus,
            "Dominica",
            "Egypt",
            "Cyprus",
            "Fiji",
            3
        )

        questions.add(que7)

        val que8 = Question(
            8,
            "What country does this flag belong to?",
            R.drawable.india,
            "Dominica",
            "Egypt",
            "Cyprus",
            "India",
            4
        )
        questions.add(que8)

        val que9 = Question(
            9,
            "What country does this flag belong to?",
            R.drawable.azerbaijan,
            "Dominica",
            "Egypt",
            "Azerbaijan",
            "India",
            3
        )
        questions.add(que9)

        val que10 = Question(
            10,
            "What country does this flag belong to?",
            R.drawable.england,
            "Dominica",
            "Egypt",
            "Azerbaijan",
            "England",
            4
        )
        questions.add(que10)

        return questions
    }
}