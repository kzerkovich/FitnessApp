package com.kzerk.fitnessapp.utils

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kzerk.fitnessapp.adapters.ExerciseModel

class MainViewModel : ViewModel() {
	val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()
	var pref: SharedPreferences? = null
	var currentDay = 0

	fun savePreferences(key: String, value: Int) {
		pref?.edit()
			?.putInt(key, value)
			?.apply()
	}

	fun getPreferences(): Int {
		return pref?.getInt(currentDay.toString(), 0) ?: 0
	}
}