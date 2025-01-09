package com.example.marvelmobileapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.marvelmobileapp.databinding.MarvelRecyclerRowBinding
import com.example.marvelmobileapp.model.MarvelData
import com.example.marvelmobileapp.util.gorselIndir
import com.example.marvelmobileapp.util.placeHolderYap
import com.example.marvelmobileapp.view.MarvelListeDirections

class Marvelrecycleradapter(val MarvelListesi : ArrayList<MarvelData>) : RecyclerView.Adapter<Marvelrecycleradapter.MarvelViewHolder>() {

    class  MarvelViewHolder(val binding: MarvelRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {
        val binding = MarvelRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MarvelViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return  MarvelListesi.size
    }

    fun marvelListesiniGuncelle(yeniMarvelListesi : List<MarvelData>){
        MarvelListesi.clear()
        MarvelListesi.addAll(yeniMarvelListesi)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {

        holder.binding.marveladisimrow.text = MarvelListesi[position].marvelİsim
        holder.binding.marvelkahramanbilgisirow.text = MarvelListesi[position].marvelKahramanÖzellikleri
        holder.itemView.setOnClickListener {
            val action = MarvelListeDirections.actionMarvelListeToMarvelDetayListe(MarvelListesi[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.marvelImagerow.gorselIndir(MarvelListesi[position].marvelGorsel,
            placeHolderYap(holder.itemView.context)
        )
    }
}