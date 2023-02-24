package com.example.andorid_movie_app.view.popular_movies

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
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
import com.example.andorid_movie_app.view.list.MovieListAdapter
import kotlinx.coroutines.launch

class PopularMoviesFragment : Fragment(), MovieListAdapter.AdapterCallback {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: PopularMoviesViewModel

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val adapter = MovieListAdapter(viewModel.list, this)
        binding.recyclerView.adapter = adapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PopularMoviesViewModel::class.java]
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        checkForInternetConnection()
        return binding.root
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
    private fun setList() {
        lifecycleScope.launch {
            val list = viewModel.getNewMovies()
            val adapter = MovieListAdapter(list, this@PopularMoviesFragment)
            viewModel.list = list
            val manager = LinearLayoutManager(context)
            binding.recyclerView.layoutManager = manager
            binding.recyclerView.adapter = adapter
        }
    }
    override fun openMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        Navigation.findNavController(binding.root).navigate(R.id.action_popularMovies_to_movieFragment, bundle)
    }

    private val networkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            setList()
        }
    }

}