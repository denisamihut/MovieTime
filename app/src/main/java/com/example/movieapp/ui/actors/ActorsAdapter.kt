package com.example.movieapp.ui.actors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class ActorsAdapter(private val actorsList: List<Actors>) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorName: TextView = view.findViewById(R.id.tv_name)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val starIcon: ImageView = view.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actor, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actorsList[position]
        holder.actorName.text = actor.name

        if (position % 2 == 0) {
            holder.parentView.setBackgroundColor(
                ContextCompat.getColor(
                    holder.parentView.context,
                    R.color.teal_200
                )
            )
        }
        holder.parentView.setOnClickListener {
            actor.isSelected = !actor.isSelected
            holder.parentView.setBackgroundColor(
                when (actor.isSelected) {
                    true -> ContextCompat.getColor(holder.parentView.context, R.color.purple_200)
                    else -> when (position % 2 == 0) {
                        true -> ContextCompat.getColor(holder.parentView.context, R.color.teal_200)
                        else -> ContextCompat.getColor(holder.parentView.context, R.color.white)
                    }
                }
            )

            holder.starIcon.visibility = when (actor.isSelected) {
                true -> View.VISIBLE
                else -> View.INVISIBLE
            }
        }
    }

    override fun getItemCount() = actorsList.size
}