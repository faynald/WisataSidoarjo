package com.farhanrv.wisatasidoarjo.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.farhanrv.wisatasidoarjo.databinding.ItemListGalleryBinding
import com.stfalcon.imageviewer.StfalconImageViewer

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.ListViewHolder>() {

    private var listData = ArrayList<String>()

    fun setData(newListData: List<String>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemListGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String) {
            with(binding) {
                binding.greyBg.visibility = View.GONE
                binding.tvItemTitle.visibility = View.GONE

                val loading = CircularProgressDrawable(itemView.context)
                loading.strokeWidth = 5f
                loading.centerRadius = 30f
                loading.start()
                Glide.with(itemView.context)
                    .load(data)
                    .override(496, 331)
                    .placeholder(loading)
                    .into(ivItemImage)
                imgFavorite.visibility = View.GONE
            }
        }

        init {
            binding.root.setOnClickListener {
                StfalconImageViewer.Builder(itemView.context, listData) { view, image ->
                    Glide.with(itemView.context).load(image).into(view)
                }.withStartPosition(adapterPosition).show()
            }
        }
    }
}