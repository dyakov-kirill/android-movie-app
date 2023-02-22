package com.example.andorid_movie_app.view.popular_movies

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_movie_app.R
import com.example.andorid_movie_app.data.Repository
import com.example.andorid_movie_app.data.RetrofitInstance
import com.example.andorid_movie_app.databinding.FragmentMoviesBinding
import com.example.andorid_movie_app.view.MovieListAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PopularMoviesFragment : Fragment(), MovieListAdapter.AdapterCallback {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            Toast.makeText(context, "Отсутствует соединение с интернетом", Toast.LENGTH_LONG).show()
        }
    }
    private fun setList() {
        lifecycleScope.launch {
            val adapter: MovieListAdapter = MovieListAdapter(viewModel.getNewMovies(), this@PopularMoviesFragment)
            val manager = LinearLayoutManager(requireContext())
            binding.recyclerView.layoutManager = manager
            binding.recyclerView.adapter = adapter
        }
    }
    override fun openMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        Navigation.findNavController(binding.root).navigate(R.id.action_popularMovies_to_movieFragment, bundle)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            setList()
        }
    }

}