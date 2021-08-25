package com.vicarial.mobile.android.mvvmdemo.ui

import androidx.lifecycle.*
import com.vicarial.mobile.android.mvvmdemo.data.WineryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WineryViewModel @Inject constructor(
    repository: WineryRepository
): ViewModel() {
    val wineries = repository.getWineries().asLiveData()
}