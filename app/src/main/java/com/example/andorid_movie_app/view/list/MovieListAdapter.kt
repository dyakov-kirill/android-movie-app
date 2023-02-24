package com.example.andorid_movie_app.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.andorid_movie_app.databinding.MovieItemBinding
import com.example.andorid_movie_app.model.MovieModel

class MovieListAdapter(private val dataSet: List<MovieModel>,
                       private val callback: AdapterCallback
) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {
    class MovieHolder(private val binding: MovieItemBinding,
                      private val callback: AdapterCallback
    ) : RecyclerView.ViewHolder(binding.root) {
        private var id: Int = 0
        fun bind(item: MovieModel) {
            binding.movieLayout.setOnClickListener {
                callback.openMovie(id)
            }
            id = item.id
            binding.textName.text =  item.name
            binding.textDescription.text = item.description
            binding.movieImage.load(item.poster!!.previewUrl)
        }

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieHolder {
        val view = MovieItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MovieHolder(view, callback)
    }

    override fun onBindViewHolder(taskHolder: MovieHolder, position: Int) {
        taskHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    interface AdapterCallback {
        fun openMovie(id: Int)
    }
}