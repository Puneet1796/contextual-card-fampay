package com.puneet.contextualcards.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.puneet.contextualcards.R
import com.puneet.contextualcards.databinding.ActivityMainBinding
import com.puneet.contextualcards.model.DataResponse
import com.puneet.contextualcards.screens.adapters.VerticalCardListAdapter
import com.puneet.contextualcards.utils.GridSpacingItemDecoration
import com.puneet.contextualcards.utils.ScreenEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private lateinit var listAdapter: VerticalCardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getDataAndDisplayResult()
    }

    private fun setupRecyclerView() {
        with(binding.cardsVerticalRv) {
            listAdapter = VerticalCardListAdapter()
            adapter = listAdapter
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.list_padding)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 1,
                    spacing = spacingInPixels,
                    includeEdge = true,
                    headerNum = 0
                )
            )
        }
    }

    private fun getDataAndDisplayResult() {
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is ScreenEvent.UpdateLoadingStateEvent -> {
                        binding.dataContainer.text = if (event.isLoading) "Fetching..." else ""
                    }
                    is ScreenEvent.DataFetched<*> -> {
                        val data = event.value as DataResponse
                        listAdapter.submitList(data.cardGroups)
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
