package com.farhanrv.wisatasidoarjo.di

import androidx.room.Room
import com.farhanrv.wisatasidoarjo.data.DataExecutors
import com.farhanrv.wisatasidoarjo.data.DataRepository
import com.farhanrv.wisatasidoarjo.data.local.DBDataSource
import com.farhanrv.wisatasidoarjo.data.local.WisataDB
import com.farhanrv.wisatasidoarjo.data.network.ApiService
import com.farhanrv.wisatasidoarjo.data.network.NetworkDataSource
import com.farhanrv.wisatasidoarjo.ui.detail.DetailViewModel
import com.farhanrv.wisatasidoarjo.ui.main.favorite.FavoriteViewModel
import com.farhanrv.wisatasidoarjo.ui.main.home.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dbModule = module {
    factory { get<WisataDB>().wisataDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            WisataDB::class.java,
            "WisataSidoarjo.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val apiModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wisata-sidoarjo-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { NetworkDataSource(get()) }
    single { DBDataSource(get()) }
    factory { DataExecutors() }
    single { DataRepository(get(), get(), get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}

























