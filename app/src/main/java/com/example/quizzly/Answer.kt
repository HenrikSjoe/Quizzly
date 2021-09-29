package com.example.quizzly


import java.io.Serializable


class Answer (
    val answer: String,
    val isCorrect: Boolean
) : Serializable