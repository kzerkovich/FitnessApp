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
import com.kzerk.fitnessapp.adapters.ExerciseAdapter
import com.kzerk.fitnessapp.databinding.ExerciseListBinding
import com.kzerk.fitnessapp.databinding.WaitingFragmentBinding
import com.kzerk.fitnessapp.utils.FragmentManager
import com.kzerk.fitnessapp.utils.MainViewModel
import com.kzerk.fitnessapp.utils.TimeUtils

const val COUNT_DOWN_TIME = 11000L
class WaitingFragment: Fragment() {
	private lateinit var binding: WaitingFragmentBinding
	private lateinit var timer: CountDownTimer


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = WaitingFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.pBar.max = COUNT_DOWN_TIME.toInt()
		startTimer()
	}

	private fun startTimer() = with(binding) {
		timer = object : CountDownTimer(COUNT_DOWN_TIME, 1) {
			override fun onTick(restTime: Long) {
				tvTimer.text = TimeUtils.getTime(restTime)
				pBar.progress = restTime.toInt()
			}

			override fun onFinish() {
				FragmentManager.setFragment(ExerciseFragment.newInstance(), activity as AppCompatActivity)
			}

		}.start()
	}

	override fun onDetach() {
		super.onDetach()
		timer.cancel()
	}

	companion object {
		@JvmStatic
		fun newInstance() = WaitingFragment()
	}
}