package com.example.andorid_movie_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.andorid_movie_app.R
import com.example.andorid_movie_app.databinding.ActivityMainBinding
import com.example.andorid_movie_app.view.popular_movies.PopularMoviesFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
       // binding.bottomNavigationView.setupWithNavController(Navigation.findNavController(binding.fragmentContainerView))
    }
}

//NavigationUI.setupWithNavController(binding.bottomNavigationView,
//findNavController(binding.fragmentContainerView.id)
//)