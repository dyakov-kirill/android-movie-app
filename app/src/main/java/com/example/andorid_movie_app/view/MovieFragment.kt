package com.example.andorid_movie_app.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.andorid_movie_app.API_KEY
import com.example.andorid_movie_app.data.RetrofitInstance
import com.example.andorid_movie_app.databinding.FragmentMovieBinding
import com.example.andorid_movie_app.model.MovieModel
import kotlinx.coroutines.launch


class MovieFragment : Fragment() {
    private var movieId : Int = 0
    private lateinit var binding: FragmentMovieBinding
    private lateinit var model: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieId = requireArguments().getInt("id")
        binding = FragmentMovieBinding.inflate(inflater)
        lifecycleScope.launch {
            model = RetrofitInstance.api.getMovieById("id", movieId, API_KEY).body()!!
            binding.textMovieName.text = model.name
            binding.textMovieDescription.text = model.description
            binding.movieRatingKp.text = "Рейтинг кинопоиска: ${model.rating.kp}"
            binding.movieRatingIMDB.text = "Рейтинг IMDB: ${model.rating.imdb}"
            binding.imagePoster.load(model.poster!!.previewUrl)
        }
        binding.kinopoiskButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kinopoisk.ru/film/${movieId}"))
            startActivity(browserIntent)
        }
        binding.imdbButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/title/${model.externalId.imdb}"))
            startActivity(browserIntent)
        }
        return binding.root
    }



}