package com.farhanrv.wisatasidoarjo.ui.main.settings

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.farhanrv.wisatasidoarjo.R
import com.farhanrv.wisatasidoarjo.databinding.FragmentSettingsBinding
import com.farhanrv.wisatasidoarjo.ui.main.home.MainActivity

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val binding: FragmentSettingsBinding by viewBinding()
    private lateinit var callback: SettingsCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            binding.tvProfileName.text = "Farhan Reynaldi Valerian"
            Glide.with(requireActivity())
                .load(R.drawable.profile_image)
                .circleCrop()
                .into(binding.imgPhotoProfile)
        }
    }

//    override fun onPrepareOptionsMenu(menu: Menu) {
//        menu.findItem(R.id.menu_placeholder).isVisible = false
//        super.onPrepareOptionsMenu(menu)
//    }

//    override fun onStart() {
//        super.onStart()
//        callback.hideSearch()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        callback.showSearch()
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        callback = context as MainActivity
//    }
}