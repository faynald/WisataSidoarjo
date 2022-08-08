package com.farhanrv.wisatasidoarjo.ui.main.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.farhanrv.wisatasidoarjo.R
import com.farhanrv.wisatasidoarjo.data.DataResource
import com.farhanrv.wisatasidoarjo.databinding.FragmentHomeBinding
import com.farhanrv.wisatasidoarjo.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()
    private lateinit var callback: HomeCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding.fragmentHomeContent.recyclerView.setHasFixedSize(true)
            val adapter = MainAdapter()
            adapter.onItemClick = { selectedData ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
            binding.fragmentHomeContent.recyclerView.adapter = adapter

            viewModel.data.observe(viewLifecycleOwner) { dataWisata ->
                if (dataWisata != null) {
                    when (dataWisata) {
                        is DataResource.Loading -> binding.fragmentHomeContent.progressBar.visibility = View.VISIBLE
                        is DataResource.Success -> {
                            binding.fragmentHomeContent.progressBar.visibility = View.GONE
                            adapter.setData(dataWisata.data)
                        }
                        is DataResource.Error -> {
                            binding.fragmentHomeContent.progressBar.visibility = View.GONE
                            binding.fragmentHomeContent.layoutError.root.visibility = View.VISIBLE
                            binding.fragmentHomeContent.layoutError.tvError.text = "Periksa Koneksi!"
                        }
                    }
                }
            }

            binding.fragmentHomeContent.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0) {
                        callback.hideSearch()
                    }

                    if (dy < 0) {
                        callback.showSearch()
                    }

                    // of the recycler view is at the first
                    // item always show the FAB
//                    if (!recyclerView.canScrollVertically(-1)) {
//                        fab.show()
//                    }
                }
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as MainActivity
    }
}