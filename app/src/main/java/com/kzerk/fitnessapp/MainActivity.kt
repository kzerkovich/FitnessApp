package com.kzerk.fitnessapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kzerk.fitnessapp.fragments.DaysFragment
import com.kzerk.fitnessapp.utils.FragmentManager

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		FragmentManager.setFragment(DaysFragment.newInstance(), this)
	}
}