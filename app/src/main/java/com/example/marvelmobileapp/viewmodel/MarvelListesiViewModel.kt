package com.example.marvelmobileapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmobileapp.model.MarvelData
import com.example.marvelmobileapp.roomdb.MarvelDatabase
import com.example.marvelmobileapp.service.MarvelApiServis
import com.example.marvelmobileapp.util.OzelSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarvelListesiViewModel(applicaton: Application) : AndroidViewModel(applicaton) {

    val marveller = MutableLiveData<List<MarvelData>>()
    val marvelHataMesaji = MutableLiveData<Boolean>()
    val marvelYukleniyor = MutableLiveData<Boolean>()



    private  val marvelApiServis = MarvelApiServis()
    private  val ozelSharedPreferences = OzelSharedPreferences(getApplication())

    private  val guncellemeZamani = 10*60*1000*1000*1000L

   fun refreshData(){
        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()

        if (kaydedilmeZamani !=null && kaydedilmeZamani != 0L && System.nanoTime()-kaydedilmeZamani<guncellemeZamani){
            //roomdan verileri alacağız
            verileriRoomdanAl()
        }
        else{
            verileriInternettenAl()
        }
    }

    fun refreshDataFromInternet(){
        verileriInternettenAl()
    }

    private  fun verileriRoomdanAl(){
        marvelYukleniyor.value = true

        viewModelScope.launch(Dispatchers.IO) {
        val marveldatasListe = MarvelDatabase(getApplication()).marvelDao().getAllMarvel()
            Log.d("RoomData", marveldatasListe.toString())
        withContext(Dispatchers.Main){
            marvelGoster(marveldatasListe)
            Toast.makeText(getApplication(),"Marvel bilgilerini Roomdan aldık",Toast.LENGTH_LONG).show()

        }

        }

    }


    private  fun verileriInternettenAl(){
        marvelYukleniyor.value = true

        viewModelScope.launch(Dispatchers.IO) {
          val marveldatasListe=  marvelApiServis.getMarvelData()
            marveldatasListe.forEach { println(it) }


            withContext(Dispatchers.Main){
                marvelYukleniyor.value = false
                //room'a kaydetme işlemi
               roomaKaydet(marveldatasListe)
               Toast.makeText(getApplication(),"Marvel bilgilerini internetten aldık",Toast.LENGTH_LONG).show()


           }

        }
    }

    private  fun marvelGoster(marveldatasListe: List<MarvelData>){
        marveller.value = marveldatasListe
        marvelHataMesaji.value = false
        marvelYukleniyor.value = false
    }

    private  fun roomaKaydet(marveldatasListe: List<MarvelData>){
        viewModelScope.launch {
            val dao = MarvelDatabase(getApplication()).marvelDao()
            dao.deleteAllMarvel()
            val uuidListesi = dao.insertAll(*marveldatasListe.toTypedArray())
            var i = 0
            while (i<marveldatasListe.size){
                marveldatasListe[i].uuid = uuidListesi[i].toInt()
                i=i+1
            }

            marvelGoster(marveldatasListe)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())

    }



}