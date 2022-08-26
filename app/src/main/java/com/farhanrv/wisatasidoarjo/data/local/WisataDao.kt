package com.farhanrv.wisatasidoarjo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WisataDao {
    @Query("SELECT * FROM wisata_list_entity")
    fun getAllWisata(): Flow<List<WisataListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWisata(list: List<WisataListEntity>)

    @Query("SELECT * FROM wisata_detail_entity WHERE wisata_id = :kode")
    fun getDetail(kode: String): Flow<List<WisataDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: List<WisataDetailEntity>)

    @Query("SELECT * FROM wisata_list_entity WHERE is_favorite = 1")
    fun getFavorites(): Flow<List<WisataListEntity>>

    @Query("UPDATE wisata_list_entity SET is_favorite = :newState WHERE kode_wisata = :kode")
    fun updateFavorite(kode: String, newState: Boolean)
}