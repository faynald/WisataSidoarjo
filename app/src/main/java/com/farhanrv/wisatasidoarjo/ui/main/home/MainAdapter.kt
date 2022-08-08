package com.farhanrv.wisatasidoarjo.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.farhanrv.wisatasidoarjo.data.local.WisataListEntity
import com.farhanrv.wisatasidoarjo.databinding.ItemListBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {

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
                val px = dpToPx(itemView.context, 16f)
                val loading = CircularProgressDrawable(itemView.context)
                loading.strokeWidth = 5f
                loading.centerRadius = 30f
                loading.start()
                Glide.with(itemView.context)
                    .load(data.thumbnail)
                    .override(496, 331)
                    .transform(CenterCrop(), RoundedCorners(px.toInt()))
                    .placeholder(loading)
                    .into(ivItemImage)
                tvItemTitle.text = data.nama
//                imgFavorite.visibility = if (data.isFavorite) View.VISIBLE else View.GONE // removed since v0.2.0
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listData[absoluteAdapterPosition]) }
        }
    }
    fun dpToPx(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}