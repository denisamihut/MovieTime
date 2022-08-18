package com.example.movieapp.ui.actors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R

import com.example.movieapp.utils.Constants.IMAGE_URL

class ActorsAdapter(private val actorsList: List<Actors>) :

    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorName: TextView = view.findViewById(R.id.tvActorName)
        val imageView: ImageView = view.findViewById(R.id.ivActorPhoto)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
        val heartIcon: ImageView = view.findViewById(R.id.starIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actorsList[position]
        holder.actorName.text = actor.name

        Glide.with(holder.imageView.context).load(IMAGE_URL + actor.profile_path)
            .into(holder.imageView)

        selectActor(holder, actor)
        holder.parentView.setOnClickListener {
            actor.isSelected = !actor.isSelected
            selectActor(holder, actor)
        }
    }

    private fun selectActor(holder: ViewHolder, actor: Actors) {
        holder.parentView.setBackgroundColor(
            when (actor.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.blue)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.white)
            }
        )

        holder.actorName.setTextColor(
            when (actor.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.black)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.black)
            }
        )

        holder.heartIcon.visibility = when (actor.isSelected) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
    }

    override fun getItemCount() = actorsList.size
}