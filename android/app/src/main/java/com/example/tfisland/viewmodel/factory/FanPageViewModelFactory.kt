package com.example.tfisland.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tfisland.model.repository.MemoryRepositoryImpl
import com.example.tfisland.model.repository.ScheduleRepositoryImpl
import com.example.tfisland.viewmodel.FanPageViewModel

class FanPageViewModelFactory(private val scheduleRepository: ScheduleRepositoryImpl,
                              private val memoryRepositoryImpl: MemoryRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FanPageViewModel::class.java)) {
            return FanPageViewModel(scheduleRepository, memoryRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
