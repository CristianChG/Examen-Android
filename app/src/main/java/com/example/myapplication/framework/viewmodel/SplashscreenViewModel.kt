package com.example.myapplication.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashscreenViewModel : ViewModel() {

    val finishedLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            delay(2000)
            finishedLoading.postValue(true)
        }
    }
}
