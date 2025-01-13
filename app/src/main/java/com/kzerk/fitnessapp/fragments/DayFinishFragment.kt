package com.kzerk.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kzerk.fitnessapp.databinding.DayFinishBinding
import com.kzerk.fitnessapp.utils.FragmentManager
import pl.droidsonroids.gif.GifDrawable

class DayFinishFragment: Fragment() {
	private lateinit var binding: DayFinishBinding



	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = DayFinishBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.imMain.setImageDrawable(GifDrawable((activity as AppCompatActivity).assets,
			"relax.gif"))

		binding.bDone.setOnClickListener {
			FragmentManager.setFragment(DaysFragment.newInstance(), activity as AppCompatActivity)
		}
	}

	companion object {
		@JvmStatic
		fun newInstance() = DayFinishFragment()
	}
}