package com.example.surveryapp.features.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.surveryapp.R
import com.example.surveryapp.databinding.SurveyRecyclerviewItemBinding
import com.example.surveryapp.persistance.entities.Survey

class SurveyRecyclerviewAdapter :
    ListAdapter<Survey, SurveyViewHolder>(object : DiffUtil.ItemCallback<Survey>() {
        override fun areItemsTheSame(oldItem: Survey, newItem: Survey): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Survey, newItem: Survey): Boolean {
            return oldItem.id.hashCode() == newItem.hashCode()
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyViewHolder {
        val root = DataBindingUtil.inflate<SurveyRecyclerviewItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.survey_recyclerview_item, parent, false
        )
        return SurveyViewHolder(root)
    }

    override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
        holder.binding.item = getItem(position)
    }
}