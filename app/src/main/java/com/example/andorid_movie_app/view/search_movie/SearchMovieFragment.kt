package com.example.andorid_movie_app.view.search_movie

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_movie_app.R
import com.example.andorid_movie_app.databinding.FragmentSearchMovieBinding
import com.example.andorid_movie_app.view.list.MovieListAdapter
import kotlinx.coroutines.launch

class SearchMovieFragment : Fragment(), MovieListAdapter.AdapterCallback {
    private lateinit var binding: FragmentSearchMovieBinding
    private lateinit var viewModel: SearchMovieViewModel

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val adapter = MovieListAdapter(viewModel.list, this)
        binding.recyclerView.adapter = adapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchMovieBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[SearchMovieViewModel::class.java]
        initList()
        setListeners()
        return binding.root
    }

    private fun checkForInternetConnection() : Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return if ((capabilities != null) && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            true
        } else {
            Toast.makeText(context, resources.getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
            false
        }
    }

    private fun setListeners() {
        binding.button.setOnClickListener {
            if (checkForInternetConnection()) {
                searchMovie(
                    binding.editTextMovieName.text.toString(),
                    binding.editTextMovieYear.text.toString(),
                    binding.editTextMovieRating.text.toString()
                )
            }
        }
    }

    private fun initList() {
        val manager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = manager
        val adapter = MovieListAdapter(listOf(), this)
        binding.recyclerView.adapter = adapter
    }

    private fun searchMovie(name: String, year: String, rating: String) {
        lifecycleScope.launch {
            val list = viewModel.searchMovie(name, year, rating)
            val adapter = MovieListAdapter(list, this@SearchMovieFragment)
            binding.recyclerView.adapter = adapter
            viewModel.list = list
        }
    }

    override fun openMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        Navigation.findNavController(binding.root).navigate(R.id.action_searchMovies_to_movieFragment, bundle)
    }
}