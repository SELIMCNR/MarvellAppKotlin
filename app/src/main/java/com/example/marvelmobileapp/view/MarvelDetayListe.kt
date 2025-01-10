package com.example.marvelmobileapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.marvelmobileapp.databinding.FragmentMarvelDetayListeBinding
import com.example.marvelmobileapp.util.gorselIndir
import com.example.marvelmobileapp.util.placeHolderYap
import com.example.marvelmobileapp.viewmodel.MarvelDetayViewModel


class MarvelDetayListe : Fragment() {

    private  var _binding:FragmentMarvelDetayListeBinding?=null
    private  val binding get() = _binding!!
    private lateinit var viewModel: MarvelDetayViewModel
    var marveluuid =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarvelDetayListeBinding.inflate(inflater,container,false)
        val view = binding.root
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MarvelDetayViewModel::class.java]

        arguments?.let {
             marveluuid = MarvelDetayListeArgs.fromBundle(it).marvelId

        }
        viewModel.roomVerisiniAl(marveluuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.marvelLiveData.observe(viewLifecycleOwner){
            it?.let {
                binding.marvelIsimdetay.text = it.marvelİsim
                binding.marvelKahramanZellikdetay.text = it.marvelKahramanÖzellikleri
                binding.marvelKahramanyas.text = it.marvelKahramanYas
                binding.marvelKahramanyasadigiyerdetay.text = it.marvelKahramanUlke
                binding.marvelKahramanCinsiyet.text = it.marvelKahramanCinsiyet
                binding.marvelImagedetay.gorselIndir(it.marvelGorsel, placeHolderYap(requireContext()))
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()



    }


}
