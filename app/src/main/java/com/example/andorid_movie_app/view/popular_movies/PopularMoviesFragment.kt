package com.example.andorid_movie_app.view.popular_movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_movie_app.data.Repository
import com.example.andorid_movie_app.databinding.FragmentMoviesBinding
import com.example.andorid_movie_app.view.MovieListAdapter
import kotlinx.coroutines.launch

class PopularMoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[PopularMoviesViewModel::class.java]
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            val adapter: MovieListAdapter = MovieListAdapter(viewModel.getNewMovies())
            val manager = LinearLayoutManager(requireContext())
            binding.recyclerView.layoutManager = manager
            binding.recyclerView.adapter = adapter
        }
        return binding.root
    }

}