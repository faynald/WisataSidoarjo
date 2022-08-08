package com.farhanrv.wisatasidoarjo.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.wisatasidoarjo.data.DataRepository

class MainViewModel(private val repository: DataRepository) : ViewModel() {

    val data = repository.getWisata().asLiveData()
}