package com.igzafer.sokak.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.igzafer.sokak.R
import com.igzafer.sokak.databinding.ActivityHomeBinding
import com.igzafer.sokak.presentation.viewmodel.NewsViewModel
import com.igzafer.sokak.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory
    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this,factory)[NewsViewModel::class.java]

    }
}