package com.example.quizzly

class Questions ( val id: Int,
                  val question: String?,
                  val answers: MutableList<Answer>

)

class Answer (
    val answer: String,
    val isCorrect: Boolean,
)



/*class Questions(var id: Int, var question: String, var answer: MutableList<Answer>) {


}

class Answer(val answer: String, val isCorrect: Boolean) {
}

 */





