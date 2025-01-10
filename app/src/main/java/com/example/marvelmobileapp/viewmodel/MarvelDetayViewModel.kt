package com.example.marvelmobileapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.marvelmobileapp.model.MarvelData
import com.example.marvelmobileapp.roomdb.MarvelDatabase
import kotlinx.coroutines.launch

class MarvelDetayViewModel(application: Application) : AndroidViewModel(application)  {

    val marvelLiveData = MutableLiveData<MarvelData>()

    fun roomVerisiniAl(uuid: Int){

        viewModelScope.launch {

            val dao = MarvelDatabase(getApplication()).marvelDao()
            val marvelDatas = dao.getMarvel(uuid)
            marvelLiveData.value = marvelDatas


        }
    }


}
