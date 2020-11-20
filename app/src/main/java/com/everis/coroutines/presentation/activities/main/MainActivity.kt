package com.everis.coroutines.presentation.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.everis.coroutines.CoroutinesApp
import com.everis.coroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}