package com.weatherapp.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.weatherapp.R
import com.weatherapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val appBarConfiguration: AppBarConfiguration by lazy(LazyThreadSafetyMode.NONE) {
        AppBarConfiguration(
            setOf()
        )
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        setupActionBar(navController, binding.toolbar)
        NavigationUI.setupWithNavController(binding.toolbar, navController)
    }

    private fun setupActionBar(navController: NavController, toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
    }
}