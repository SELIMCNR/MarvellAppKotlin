package com.example.marvelmobileapp.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.marvelmobileapp.model.MarvelData


@Dao
interface MarvelDAO {


    @Insert
    suspend fun insertAll(vararg marvel : MarvelData) : List<Long>
    //eklediği besinlerin id'sini long olarak döndürüyor

    @Query("SELECT * FROM marveldata")
    suspend fun getAllMarvel() : List<MarvelData>

    @Query("SELECT * FROM marveldata WHERE uuid = :marvelId")
    suspend fun getMarvel(marvelId : Int) : MarvelData

    @Query("Delete  FROM marveldata")
    suspend fun deleteAllMarvel()



}