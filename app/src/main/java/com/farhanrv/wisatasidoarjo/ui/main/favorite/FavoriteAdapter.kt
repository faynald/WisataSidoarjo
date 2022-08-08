package com.farhanrv.wisatasidoarjo.ui.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.farhanrv.wisatasidoarjo.databinding.ItemListBinding
import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {

    private var listData = ArrayList<WisataListEntity>()
    var onItemClick: ((WisataListEntity) -> Unit)? = null

    fun setData(newListData: List<WisataListEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WisataListEntity) {
            with(binding) {
                val loading = CircularProgressDrawable(itemView.context)
                loading.strokeWidth = 5f
                loading.centerRadius = 30f
                loading.start()
                Glide.with(itemView.context)
                    .load(data.thumbnail)
                    .override(496, 331)
                    .placeholder(loading)
                    .into(ivItemImage)
                tvItemTitle.text = data.nama
                imgFavorite.visibility = View.GONE
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listData[adapterPosition]) }
        }
    }
}