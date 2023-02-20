package com.example.andorid_movie_app.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.andorid_movie_app.databinding.MovieItemBinding
import com.example.andorid_movie_app.model.MovieModel

class MovieListAdapter(private val dataSet: List<MovieModel>) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {
    class MovieHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var id: Int = 0
        fun bind(item: MovieModel) {
            binding.movieLayout.setOnClickListener {}
            id = item.id
            binding.textName.text = item.name
            binding.textDescription.text = item.description
            binding.movieImage.load(item.poster!!.previewUrl)
        }

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieHolder {
        val view = MovieItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(taskHolder: MovieHolder, position: Int) {
        taskHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}