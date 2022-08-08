package com.farhanrv.wisatasidoarjo.ui.main.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.wisatasidoarjo.data.DataRepository

class FavoriteViewModel(private val repository: DataRepository) : ViewModel() {

    val data = repository.getFavorites().asLiveData()
}