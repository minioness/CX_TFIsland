package com.example.tfisland.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tfisland.model.repository.ArtistRepositoryImpl
import com.example.tfisland.model.repository.CaseUserRepositoryImpl

import com.example.tfisland.viewmodel.InfoPageViewModel

class InfoPageViewModelFactory(
    private val artistRepository: ArtistRepositoryImpl,
    private val caseUserRepositoryImpl: CaseUserRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoPageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InfoPageViewModel(artistRepository, caseUserRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

