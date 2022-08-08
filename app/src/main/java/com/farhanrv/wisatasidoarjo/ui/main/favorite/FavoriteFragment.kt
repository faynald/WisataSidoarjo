package com.farhanrv.wisatasidoarjo.ui.main.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.farhanrv.wisatasidoarjo.R
import com.farhanrv.wisatasidoarjo.databinding.FragmentFavoriteBinding
import com.farhanrv.wisatasidoarjo.ui.detail.DetailActivity
import com.farhanrv.wisatasidoarjo.ui.main.home.HomeCallback
import com.farhanrv.wisatasidoarjo.ui.main.home.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var callback: HomeCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding.fragmentFavoriteContent.recyclerView.setHasFixedSize(true)
            val adapter = FavoriteAdapter()
            adapter.onItemClick = { selectedData ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
            binding.fragmentFavoriteContent.recyclerView.adapter = adapter

            viewModel.data.observe(viewLifecycleOwner) { dataWisata ->
                if (dataWisata != null) {
                    adapter.setData(dataWisata)

                }
            }

            binding.fragmentFavoriteContent.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0) {
                        callback.hideSearch()
                    }

                    if (dy < 0) {
                        callback.showSearch()
                    }
                }
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as MainActivity
    }
}