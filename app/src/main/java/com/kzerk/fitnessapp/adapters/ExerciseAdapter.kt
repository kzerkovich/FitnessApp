package com.kzerk.fitnessapp.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kzerk.fitnessapp.R
import com.kzerk.fitnessapp.databinding.ExerciseListItemBinding
import pl.droidsonroids.gif.GifDrawable

class ExerciseAdapter() : ListAdapter<ExerciseModel, ExerciseAdapter.ExerciseHolder>(Comparator()) {

	class ExerciseHolder(view: View) : RecyclerView.ViewHolder(view) {
		private val binding = ExerciseListItemBinding.bind(view)

		fun setData(exercise: ExerciseModel) =  with(binding) {
			tvName.text = exercise.name
			tvCount.text = exercise.time
			imExercise.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
		}
	}

	class Comparator : DiffUtil.ItemCallback<ExerciseModel>() {
		override fun areItemsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
			return oldItem == newItem
		}

		override fun areContentsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
			return oldItem == newItem
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.exercise_list_item, parent, false)
		return ExerciseHolder(view)
	}

	override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
		holder.setData(getItem(position))
	}

	interface Listener {
		fun onClick(day: DayModel)
	}
}