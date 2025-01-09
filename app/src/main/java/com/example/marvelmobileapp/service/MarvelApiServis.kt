package com.example.marvelmobileapp.service

import com.example.marvelmobileapp.model.MarvelData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelApiServis {

   private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MarvelApı::class.java)

    suspend fun getMarvelData() : List<MarvelData> {
        return retrofit.getMarvelData()
    }



}