package ru.harlion.psy.ui.exercise.child.edit.wish_diary

import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx

class EditWishViewModel: ViewModel() {

    private val repo = Repository.get()

    fun add(
        fieldOne : String,
        dateCreate: Long,
        dateDone: Long,
        listSteps: List<String>,
    ){
        val exercise = Exercise(
            fieldOne = fieldOne,
            date = dateDone,
            dateCreate = dateCreate,
            listString = listSteps,
            type = TypeEx.WISH_DIARY
        )
        repo.addExercise(exercise)
    }

    fun update(){

    }

    fun delete(){

    }
}