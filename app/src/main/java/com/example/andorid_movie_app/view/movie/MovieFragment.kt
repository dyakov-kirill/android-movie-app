package com.example.andorid_movie_app.view.movie

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibratorManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.andorid_movie_app.API_KEY
import com.example.andorid_movie_app.IMDB_URL
import com.example.andorid_movie_app.KP_URL
import com.example.andorid_movie_app.R
import com.example.andorid_movie_app.data.api.RetrofitInstance
import com.example.andorid_movie_app.data.db.FavoriteDatabase
import com.example.andorid_movie_app.databinding.FragmentMovieBinding
import com.example.andorid_movie_app.model.IdModel
import com.example.andorid_movie_app.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieFragment : Fragment() {
    private var movieId : Int = 0
    private lateinit var binding: FragmentMovieBinding
    private lateinit var model: MovieModel
    private lateinit var viewModel: MovieViewModel

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieId = requireArguments().getInt("id")
        binding = FragmentMovieBinding.inflate(inflater)
        setListeners()
        lifecycleScope.launch {
            val idModel: IdModel?
            withContext(Dispatchers.IO) {
                idModel = FavoriteDatabase.getInstance(requireContext()).getTaskDao().getById(movieId)
                model = RetrofitInstance.api.getMovieById("id", movieId, API_KEY).body()!!
            }
            setData(idModel)
        }
        return binding.root
    }

    private fun setData(idModel: IdModel?) {
        binding.textMovieName.text = model.name
        binding.textMovieDescription.text = model.description
        binding.movieRatingKp.text = String.format(resources.getString(R.string.kp_rating), model.rating.kp)
        binding.movieRatingIMDB.text = String.format(resources.getString(R.string.imdb_rating), model.rating.imdb)
        binding.imagePoster.load(model.poster!!.previewUrl)
        if (idModel != null) {
            viewModel.isFavorite = true
            binding.imageView.colorFilter =
                PorterDuffColorFilter(Color.parseColor("#ffd800"), PorterDuff.Mode.SRC_IN)
            binding.favoriteButton.text = resources.getText(R.string.delete_from_favorite)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setListeners() {
        binding.kinopoiskButton.setOnClickListener {
            openURL("${KP_URL}${movieId}")
        }
        binding.imdbButton.setOnClickListener {
            openURL("${IMDB_URL}${model.externalId.imdb}")
        }
        binding.favoriteButton.setOnClickListener {
            makeVibration()
            switchFavorite()
        }
    }

    private fun switchFavorite() {
        if (!viewModel.isFavorite) {
            lifecycleScope.launch(Dispatchers.IO) {
                FavoriteDatabase.getInstance(requireContext()).getTaskDao()
                    .addFavorite(IdModel(movieId))
            }
            viewModel.isFavorite = true
            binding.imageView.colorFilter =
                PorterDuffColorFilter(Color.parseColor("#ffd800"), PorterDuff.Mode.SRC_IN)
            binding.favoriteButton.text = resources.getText(R.string.delete_from_favorite)
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                FavoriteDatabase.getInstance(requireContext()).getTaskDao()
                    .deleteFavorite(movieId)
            }
            viewModel.isFavorite = false
            binding.imageView.colorFilter =
                PorterDuffColorFilter(Color.parseColor("#8f9193"), PorterDuff.Mode.SRC_IN)
            binding.favoriteButton.text = resources.getText(R.string.add_to_favorite)
        }
    }

    private fun openURL(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun makeVibration() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibrator.defaultVibrator.vibrate(VibrationEffect.createOneShot(100, 1))
    }



}