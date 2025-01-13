package com.kzerk.fitnessapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kzerk.fitnessapp.adapters.ExerciseModel

class MainViewModel : ViewModel() {
	val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()


}