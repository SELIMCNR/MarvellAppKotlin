package com.example.marvelmobileapp.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelmobileapp.R

/*
//Kotlin classına dışardan fonksiyon ekleme
fun String.benimEklentim(parametre:String){
    println(parametre)

    //Oluşturulan fonksiyonu herhangi bir stringde çalıştırma
    var x = ""
    x.benimEklentim("a")
}*/

fun ImageView.gorselIndir(url:String?,placeholder: CircularProgressDrawable){
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun placeHolderYap(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth =8f
        centerRadius = 40f
        start()
    }
}