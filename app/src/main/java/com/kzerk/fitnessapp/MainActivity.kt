package com.kzerk.fitnessapp

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kzerk.fitnessapp.fragments.DaysFragment
import com.kzerk.fitnessapp.utils.FragmentManager
import com.kzerk.fitnessapp.utils.MainViewModel

class MainActivity : AppCompatActivity() {
	private val model: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		model.pref = getSharedPreferences("main", MODE_PRIVATE)
		FragmentManager.setFragment(DaysFragment.newInstance(), this)

		onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				if (FragmentManager.currentFragment is DaysFragment) finish()
				else FragmentManager.setFragment(DaysFragment.newInstance(), this@MainActivity)
			}
		})
	}
}