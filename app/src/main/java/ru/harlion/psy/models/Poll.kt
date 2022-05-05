package ru.harlion.psy.models

class Poll(
    val id : Long,
    val dateCreate: Long,
    val questions: List<String>,
    val assessments: List<Int>
)