package com.kzerk.fitnessapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kzerk.fitnessapp.R

object FragmentManager {
	var currentFragment: Fragment? = null

	fun setFragment(fragment: Fragment, activity: AppCompatActivity) {
		val transaction = activity.supportFragmentManager.beginTransaction()
		transaction.replace(R.id.placeHolder, fragment).commit()
		currentFragment = fragment
	}
}