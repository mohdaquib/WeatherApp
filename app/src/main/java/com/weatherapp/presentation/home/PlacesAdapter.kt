package com.weatherapp.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.weatherapp.R
import com.weatherapp.presentation.core.DataBindingAdapter
import com.weatherapp.presentation.core.DataBindingViewHolder
import com.weatherapp.presentation.places.Place
import kotlinx.android.synthetic.main.item_places.view.*

class PlacesAdapter : DataBindingAdapter<Place>(
    DiffCallback()
) {
    var onClick: ((place: Place) -> Unit)? = null
    var onCloseClick: ((place: Place) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder<Place> {
        val holder = super.onCreateViewHolder(parent, viewType)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = getItem(position)
            onClick?.invoke(place)
        }

        holder.itemView.close.setOnClickListener {
            val place = getItem(holder.adapterPosition)
            onCloseClick?.invoke(place)
        }

        return holder
    }

    class DiffCallback : DiffUtil.ItemCallback<Place>() {
        // your DiffCallback implementation
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.postalCode == newItem.postalCode
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_places
    }
}