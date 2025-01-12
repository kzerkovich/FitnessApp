package com.kzerk.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kzerk.fitnessapp.R
import com.kzerk.fitnessapp.adapters.DayModel
import com.kzerk.fitnessapp.adapters.DaysAdapter
import com.kzerk.fitnessapp.databinding.FragmentDaysBinding

class DaysFragment : Fragment() {
	private lateinit var binding: FragmentDaysBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentDaysBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initRC()
	}

	private fun fillDays() : ArrayList<DayModel> {
		val tArray = ArrayList<DayModel>()
		resources.getStringArray(R.array.day_exercises).forEach {
			tArray.add(DayModel(it, false))
		}
		return tArray
	}

	private fun initRC() = with(binding) {
		val adapter = DaysAdapter()
		rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
		rcViewDays.adapter = adapter
		adapter.submitList(fillDays())
	}

	companion object {
		@JvmStatic
		fun newInstance() = DaysFragment()
	}
}