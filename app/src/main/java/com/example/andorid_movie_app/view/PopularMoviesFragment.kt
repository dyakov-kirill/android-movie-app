package com.example.andorid_movie_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_movie_app.R
import com.example.andorid_movie_app.data.Repository
import com.example.andorid_movie_app.data.RetrofitInstance
import com.example.andorid_movie_app.databinding.FragmentMoviesBinding
import kotlinx.coroutines.launch

class PopularMoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            val Repo = Repository()
            val movie = Repo.getNew().body()!!
            val adapter = MovieListAdapter(movie.movies)
            val manager = LinearLayoutManager(requireContext())
            binding.recyclerView.layoutManager = manager
            binding.recyclerView.adapter = adapter
        }
        return binding.root
    }

}