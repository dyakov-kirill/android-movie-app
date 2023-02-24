package com.example.andorid_movie_app.view.favorite_movies

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
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
import com.example.andorid_movie_app.databinding.FragmentMoviesBinding
import com.example.andorid_movie_app.model.MovieModel
import com.example.andorid_movie_app.view.list.MovieListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteMoviesFragment : Fragment(), MovieListAdapter.AdapterCallback {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: FavoriteMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[FavoriteMoviesViewModel::class.java ]
        binding = FragmentMoviesBinding.inflate(inflater)
        checkForInternetConnection()
        return binding.root
    }

    private fun setList() {
        lifecycleScope.launch {
            val list: List<MovieModel>
            withContext(Dispatchers.IO) {
                list = viewModel.getFavorites()
            }
            val adapter = MovieListAdapter(list, this@FavoriteMoviesFragment)
            val manager = LinearLayoutManager(requireContext())
            binding.recyclerView.layoutManager = manager
            binding.recyclerView.adapter = adapter
        }
    }

    override fun openMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        Navigation.findNavController(binding.root).navigate(R.id.action_favoriteMoviesFragment_to_movieFragment, bundle)
    }

    private fun checkForInternetConnection() {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            setList()
        } else {
            Toast.makeText(context, resources.getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            setList()
        }
    }
}