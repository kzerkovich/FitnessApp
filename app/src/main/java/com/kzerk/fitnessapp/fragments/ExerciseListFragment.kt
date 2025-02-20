package com.kzerk.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kzerk.fitnessapp.adapters.ExerciseAdapter
import com.kzerk.fitnessapp.databinding.ExerciseListBinding
import com.kzerk.fitnessapp.utils.FragmentManager
import com.kzerk.fitnessapp.utils.MainViewModel


class ExerciseListFragment: Fragment() {
	private lateinit var binding: ExerciseListBinding
	private lateinit var adapter: ExerciseAdapter
	private val model : MainViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = ExerciseListBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		init()
		model.mutableListExercise.observe(viewLifecycleOwner) {
			for (i in 0 until model.getPreferences()) {
				it[i] = it[i].copy(isDone = true)
			}
			adapter.submitList(it)
		}
	}

	private fun init() = with(binding) {
		adapter = ExerciseAdapter()
		rcView.layoutManager = LinearLayoutManager(activity)
		rcView.adapter = adapter
		bStart.setOnClickListener {
			FragmentManager.setFragment(WaitingFragment.newInstance(), activity as AppCompatActivity)
		}
	}

	companion object {
		@JvmStatic
		fun newInstance() = ExerciseListFragment()
	}
}