package com.example.tfisland.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tfisland.model.repository.CaseUserRepositoryImpl
import com.example.tfisland.model.repository.ScheduleRepositoryImpl
import com.example.tfisland.viewmodel.MainPageViewModel

class MainPageViewModelFactory(
    private val caseUserRepository: CaseUserRepositoryImpl,
    private val scheduleRepository: ScheduleRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainPageViewModel::class.java)) {
            return MainPageViewModel(caseUserRepository, scheduleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
