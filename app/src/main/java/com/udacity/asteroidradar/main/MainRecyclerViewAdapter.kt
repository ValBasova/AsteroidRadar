package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class MainRecyclerViewAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>() {
    var data = listOf<Asteroid>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        holder.codeNameText.text = item.codename
        holder.approachDate.text = item.closeApproachDate

        holder.hazardousImageView.setImageResource(
            if (item.isPotentiallyHazardous) R.drawable.ic_status_potentially_hazardous
            else R.drawable.ic_status_normal
        )
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.asteroid_item_view, parent, false
        )
        return MyViewHolder(view)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codeNameText: TextView = itemView.findViewById(R.id.code_name_text)
        val approachDate: TextView = itemView.findViewById(R.id.approach_date)
        val hazardousImageView: ImageView = itemView.findViewById(R.id.hazardous_imageView)
    }


    class OnClickListener(val clickListener: (marsProperty: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }
}