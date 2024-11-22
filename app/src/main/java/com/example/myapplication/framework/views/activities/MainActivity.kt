package com.example.myapplication.framework.views.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.framework.adapters.HistoricalEventAdapter
import com.example.myapplication.framework.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = HistoricalEventAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        initializeBinding()
        initializeRecyclerView()
        initializeObservers()
        initializeListeners()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeRecyclerView() {
        binding.rvHistoricalEvents.layoutManager = LinearLayoutManager(this)
        binding.rvHistoricalEvents.adapter = adapter
    }

    private fun initializeObservers() {
        viewModel.historicalEvents.observe(this) { events ->
            if (events != null) {
                adapter.submitList(events)
            } else {
                Toast.makeText(this, "No se pudieron cargar los datos.", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(this) { error ->
            if (error) {
                Toast.makeText(this, "Error al cargar los datos. Intente nuevamente.", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.currentPage.observe(this) { page ->
            binding.btnPreviousPage.isEnabled = page > 1
        }
    }

    private fun initializeListeners() {
        binding.svCategorySearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchByQuery(newText.orEmpty())
                return true
            }
        })

        binding.btnNextPage.setOnClickListener {
            viewModel.loadNextPage()
        }

        binding.btnPreviousPage.setOnClickListener {
            viewModel.loadPreviousPage()
        }
    }
}
