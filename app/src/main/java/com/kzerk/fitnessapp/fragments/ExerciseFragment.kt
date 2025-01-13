package com.kzerk.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kzerk.fitnessapp.R
import com.kzerk.fitnessapp.adapters.ExerciseAdapter
import com.kzerk.fitnessapp.adapters.ExerciseModel
import com.kzerk.fitnessapp.databinding.ExerciseBinding
import com.kzerk.fitnessapp.databinding.ExerciseListBinding
import com.kzerk.fitnessapp.utils.FragmentManager
import com.kzerk.fitnessapp.utils.MainViewModel
import com.kzerk.fitnessapp.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {
	private lateinit var binding: ExerciseBinding
	private var counter = 0
	private var exList: ArrayList<ExerciseModel>? = null
	private val model: MainViewModel by activityViewModels()
	private var timer: CountDownTimer? = null
	private var curDay = 0

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = ExerciseBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		curDay = model.currentDay
		counter = model.getPreferences()
		model.mutableListExercise.observe(viewLifecycleOwner) {
			exList = it
			nextExercise()
		}

		binding.btNext.setOnClickListener {
			nextExercise()
		}
	}

	private fun nextExercise() {
		if (counter < exList?.size!!) {
			val ex = exList?.get(counter++) ?: return
			showExercise(ex)
			setExerciseType(ex)
			showNextExercise()
		} else {
			counter++
			FragmentManager.setFragment(DayFinishFragment.newInstance(), activity as AppCompatActivity)
		}
	}

	private fun showExercise(exercise: ExerciseModel) = with(binding) {
		imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
		tvName.text = exercise.name
	}

	private fun setExerciseType(exercise: ExerciseModel) = with(binding){
		timer?.cancel()
		if (exercise.time.startsWith("x")) {
			progressBar.visibility = View.INVISIBLE
			tvTime.text = exercise.time
		} else {
			progressBar.visibility = View.VISIBLE
			startTimer(exercise)
		}
	}

	private fun setTimeType(exercise: ExerciseModel) = with(binding){
		if (exercise.time.startsWith("x")) {
			val text = exercise.name + ": " + exercise.time
			tvNextName.text = text
		} else {
			val name = exercise.name + ": ${TimeUtils.getTime(exercise.time.toLong() * 1000)}"
			tvNextName.text = name
		}
	}

	private fun startTimer(exercise: ExerciseModel) = with(binding) {
		progressBar.max = exercise.time.toInt() * 1000
		timer = object : CountDownTimer(exercise.time.toLong() * 1000L, 1) {
			override fun onTick(restTime: Long) {
				tvTime.text = TimeUtils.getTime(restTime)
				progressBar.progress = restTime.toInt()
			}

			override fun onFinish() {
				nextExercise()
			}

		}.start()
	}

	private fun showNextExercise() = with(binding) {
		if (counter < exList?.size!!) {
			val ex = exList?.get(counter) ?: return
			imNext.setImageDrawable(GifDrawable(root.context.assets, ex.image))
			setTimeType(ex)
		} else {
			imNext.setImageDrawable(GifDrawable(root.context.assets, "relax.gif"))
			tvNextName.text = getString(R.string.back_to_days)

		}
	}

	override fun onDetach() {
		super.onDetach()
		model.savePreferences(curDay.toString(), counter - 1)
		timer?.cancel()
	}

	companion object {
		@JvmStatic
		fun newInstance() = ExerciseFragment()
	}
}