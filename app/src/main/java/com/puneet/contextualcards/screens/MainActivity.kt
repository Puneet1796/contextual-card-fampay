package com.puneet.contextualcards.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.puneet.contextualcards.R
import com.puneet.contextualcards.databinding.ActivityMainBinding
import com.puneet.contextualcards.utils.ScreenEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataAndDisplayResult()
    }

    private fun getDataAndDisplayResult() {
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is ScreenEvent.UpdateLoadingStateEvent -> {
                        binding.dataContainer.text = if (event.isLoading) "Fetching..." else ""
                    }
                    is ScreenEvent.DataFetched<*> -> {
                        binding.dataContainer.text = "Success! Got the data"
                    }
                    is ScreenEvent.ErrorEvent -> {
                        binding.dataContainer.text = if (event.message.isNullOrEmpty()) {
                            getString(R.string.default_error_message)
                        } else {
                            event.message
                        }
                    }
                }
            }
        }
        viewModel.getData()
    }
}
