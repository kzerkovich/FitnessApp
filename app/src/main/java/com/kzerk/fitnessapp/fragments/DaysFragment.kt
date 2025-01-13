package com.kzerk.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kzerk.fitnessapp.R
import com.kzerk.fitnessapp.adapters.DayModel
import com.kzerk.fitnessapp.adapters.DaysAdapter
import com.kzerk.fitnessapp.adapters.ExerciseModel
import com.kzerk.fitnessapp.databinding.FragmentDaysBinding
import com.kzerk.fitnessapp.utils.FragmentManager
import com.kzerk.fitnessapp.utils.MainViewModel

class DaysFragment : Fragment(), DaysAdapter.Listener {
	private lateinit var binding: FragmentDaysBinding
	private val model: MainViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentDaysBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		model.currentDay = 0
		initRC()
	}

	private fun fillDays(): ArrayList<DayModel> {
		val tArray = ArrayList<DayModel>()
		var dayCounter = 0
		resources.getStringArray(R.array.day_exercises).forEach {
			model.currentDay++
			val exCounter = it.split(",").size
			tArray.add(DayModel(it, 0, model.getPreferences() == exCounter))
		}
		binding.progressBar.max = tArray.size
		tArray.forEach {
			if (it.isDone) dayCounter++
		}
		updateDays(tArray.size - dayCounter, tArray.size)
		return tArray
	}

	private fun updateDays(restDays: Int, days: Int) = with(binding) {
		val rDays = restDays.toString() + " " + getString(R.string.rest_days)
		remainingDays.text = rDays
		progressBar.progress = days - restDays
	}

	private fun initRC() = with(binding) {
		val adapter = DaysAdapter(this@DaysFragment)
		rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
		rcViewDays.adapter = adapter
		adapter.submitList(fillDays())
	}

	private fun fillExercise(day: DayModel) {
		val tList = ArrayList<ExerciseModel>()
		day.exercises.split(",").forEach {
			val exerciseList = resources.getStringArray(R.array.exercise)
			val exercise = exerciseList[it.toInt()]
			val exerciseArray = exercise.split("|")
			tList.add(
				ExerciseModel(exerciseArray[0], exerciseArray[1], exerciseArray[2], false)
			)
		}

		model.mutableListExercise.value = tList
	}

	companion object {
		@JvmStatic
		fun newInstance() = DaysFragment()
	}

	override fun onClick(day: DayModel) {
		fillExercise(day)
		model.currentDay = day.dayNumber
		FragmentManager.setFragment(
			ExerciseListFragment.newInstance(),
			activity as AppCompatActivity
		)
	}
}