package com.example.andorid_movie_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andorid_movie_app.R
import com.example.andorid_movie_app.databinding.FragmentSearchFilmBinding

class SearchFilmFragment : Fragment() {

    lateinit var binding: FragmentSearchFilmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchFilmBinding.inflate(inflater)
        return binding.root
    }
}