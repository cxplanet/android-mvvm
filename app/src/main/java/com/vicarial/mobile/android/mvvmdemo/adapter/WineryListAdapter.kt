package com.vicarial.mobile.android.mvvmdemo.adapter

import com.vicarial.mobile.android.mvvmdemo.data.Winery
import com.vicarial.mobile.android.mvvmdemo.databinding.WineryItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WineryListAdapter: ListAdapter<Winery, WineryListAdapter.WineryViewHolder>(
    WineryComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WineryViewHolder {
        val binding =
            WineryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WineryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WineryViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    class WineryViewHolder(private val binding: WineryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(winery: Winery) {
            binding.apply {
//                Glide.with(itemView)
//                    .load(getResources()
//                        .getIdentifier("winetour_logo", "drawable", this.getPackageName())
//                        .into(myImageView);

                wineryCardName.text = winery.name
                wineryCardAddress.text = winery.address
                wineryCardCityStateZip.text = winery.cityStateZip
            }

        }

    }

    class WineryComparator : DiffUtil.ItemCallback<Winery>() {
        override fun areItemsTheSame(oldItem: Winery, newItem: Winery): Boolean {
            return (oldItem.name == newItem.name)
        }

        override fun areContentsTheSame(oldItem: Winery, newItem: Winery): Boolean {
            // because its a data class, you can compare all the fields like this
            return (oldItem == newItem)
        }

    }

}