package com.example.andorid_movie_app.view.search_movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_movie_app.R
import com.example.andorid_movie_app.databinding.FragmentSearchMovieBinding
import com.example.andorid_movie_app.model.MovieModel
import com.example.andorid_movie_app.view.MovieListAdapter
import kotlinx.coroutines.launch

class SearchMovieFragment : Fragment(), MovieListAdapter.AdapterCallback {
    lateinit var binding: FragmentSearchMovieBinding
    lateinit var viewModel: SearchMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMovieBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[SearchMovieViewModel::class.java]
        val manager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = manager
        val adapter: MovieListAdapter = MovieListAdapter(listOf(), this@SearchMovieFragment)
        binding.recyclerView.adapter = adapter
        binding.button.setOnClickListener {
            searchMovie(binding.editTextMovieName.text.toString(),
                binding.editTextMovieYear.text.toString(), binding.editTextMovieRating.text.toString())
        }
        return binding.root
    }

    private fun searchMovie(name: String, year: String, rating: String) {
        lifecycleScope.launch {
            val adapter: MovieListAdapter = MovieListAdapter(viewModel.searchMovie(name, year, rating), this@SearchMovieFragment)
            binding.recyclerView.adapter = adapter
        }
    }

    override fun openMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        Navigation.findNavController(binding.root).navigate(R.id.action_popularMovies_to_movieFragment, bundle)
    }
}