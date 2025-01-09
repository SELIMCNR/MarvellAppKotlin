package com.example.marvelmobileapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelmobileapp.adapter.Marvelrecycleradapter
import com.example.marvelmobileapp.databinding.FragmentMarvelListeBinding
import com.example.marvelmobileapp.service.MarvelApiServis
import com.example.marvelmobileapp.viewmodel.MarvelListesiViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class MarvelListe : Fragment() {

    private var _binding:FragmentMarvelListeBinding? = null
    private  val binding get() = _binding!!
    private  lateinit var viewModel: MarvelListesiViewModel
    private  val recyclerMarvelAdapter = Marvelrecycleradapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().getSharedPreferences("com.example.marvelmobileapp", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarvelListeBinding.inflate(inflater,container,false)
        val  view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this)[MarvelListesiViewModel::class.java]

        viewModel.refreshData()

        binding.marvelrecyclerView.adapter = recyclerMarvelAdapter

        binding.marvelrecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.SwipeRefreshLayout.setOnRefreshListener {
            binding.marvelrecyclerView.visibility = View.GONE
            binding.marvelapphatamesaj.visibility = View.GONE
            binding.marvelYukleniyor .visibility = View.VISIBLE
            viewModel.refreshDataFromInternet()
            binding.SwipeRefreshLayout.isRefreshing = false


        }

        observeLiveData()

        /*
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BesinApı::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val besinler = retrofit.getMarvelData()
            besinler.forEach {
                println(it.marvelİsim)
            }
        }*/
    }

    private  fun observeLiveData(){
        viewModel.marveller.observe(viewLifecycleOwner){
            //adapter
            recyclerMarvelAdapter.marvelListesiniGuncelle(it)
            binding.marvelrecyclerView.visibility = View.VISIBLE
        }

        viewModel.marvelHataMesaji.observe(viewLifecycleOwner){
            if (it){
                binding.marvelapphatamesaj.visibility = View.VISIBLE
                binding.marvelrecyclerView.visibility = View.GONE
            }
            else{
                binding.marvelapphatamesaj.visibility = View.GONE
            }
        }

        viewModel.marvelYukleniyor.observe(viewLifecycleOwner){
            if (it){
                binding.marvelapphatamesaj.visibility = View.VISIBLE
                binding.marvelrecyclerView.visibility = View.GONE
                binding.marvelYukleniyor.visibility = View.VISIBLE
            }
            else{
                binding.marvelYukleniyor.visibility = View.GONE
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}