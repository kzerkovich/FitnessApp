package com.kzerk.fitnessapp

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kzerk.fitnessapp.fragments.DaysFragment
import com.kzerk.fitnessapp.utils.FragmentManager

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		FragmentManager.setFragment(DaysFragment.newInstance(), this)

		onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				if (FragmentManager.currentFragment is DaysFragment) finish()
				else FragmentManager.setFragment(DaysFragment.newInstance(), this@MainActivity)
			}
		})
	}
}