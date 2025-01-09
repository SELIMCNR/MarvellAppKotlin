package com.example.marvelmobileapp.service

import com.example.marvelmobileapp.model.MarvelData
import retrofit2.http.GET

interface MarvelApı {
    /*
    //Örnek kullanım
    //api.google.com/updatedb
    @GET("updatedb")
    fun getBesin() : List<MarvelData>
    //api.google.com/readusers
    fun readUsers() : List<MarvelData>
    //api.google.com/createuser
    fun createUsers() : List<MarvelData>
*/
    //https://raw.githubusercontent.com/SELIMCNR/MarvelJsonData/refs/heads/main/marvel.json

    //BASE URL -> https://raw.githubusercontent.com/
    //ENDPOINT -> SELIMCNR/MarvelJsonData/refs/heads/main/marvel.json


    @GET("SELIMCNR/MarvelJsonData/refs/heads/main/marvel.json")
    suspend fun getMarvelData() : List<MarvelData>




}