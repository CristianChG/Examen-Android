package com.example.myapplication.framework.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.network.model.HistoricalEvent
import com.example.myapplication.domain.HistoricalRequirement
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val historicalRequirement = HistoricalRequirement()

    private val _historicalEvents = MutableLiveData<List<HistoricalEvent>?>()
    private val _filteredHistoricalEvents = MutableLiveData<List<HistoricalEvent>?>()
    val historicalEvents: LiveData<List<HistoricalEvent>?> get() = _filteredHistoricalEvents

    private val _currentPage = MutableLiveData(1)
    val currentPage: LiveData<Int> get() = _currentPage

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error

    init {
        loadHistoricalEvents(1)
    }

    private fun loadHistoricalEvents(page: Int) {
        viewModelScope.launch {
            try {
                val events = historicalRequirement(page)
                if (events != null) {
                    Log.d("VIEWMODEL", "Eventos cargados: ${events.size}")
                    _historicalEvents.postValue(events)
                    _filteredHistoricalEvents.postValue(events)
                    _currentPage.postValue(page)
                } else {
                    Log.e("VIEWMODEL", "No se pudieron cargar eventos.")
                    _error.postValue(true)
                }
            } catch (e: Exception) {
                Log.e("VIEWMODEL_EXCEPTION", "Excepción: ${e.message}")
                _error.postValue(true)
            }
        }
    }

    fun searchByQuery(query: String) {
        val events = _historicalEvents.value
        if (events != null) {
            val filteredEvents = if (query.isEmpty()) {
                events
            } else {
                events.filter { event ->
                    event.category1.startsWith(query, ignoreCase = true) ||
                            event.date.startsWith(query)
                }
            }
            _filteredHistoricalEvents.postValue(filteredEvents)
        }
    }

    fun loadNextPage() {
        val nextPage = (_currentPage.value ?: 1) + 1
        loadHistoricalEvents(nextPage)
    }

    fun loadPreviousPage() {
        val previousPage = (_currentPage.value ?: 1) - 1
        if (previousPage > 0) {
            loadHistoricalEvents(previousPage)
        }
    }
}
