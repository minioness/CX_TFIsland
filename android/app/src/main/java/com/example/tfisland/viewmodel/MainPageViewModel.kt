package com.example.tfisland.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfisland.model.dto.CaseUserResponse
import com.example.tfisland.model.dto.RecentScheduleResponse
import com.example.tfisland.model.repository.CaseUserRepositoryImpl
import com.example.tfisland.model.repository.ScheduleRepositoryImpl
import kotlinx.coroutines.launch

class MainPageViewModel(
    private val caseUserRepository: CaseUserRepositoryImpl,
    private val scheduleRepository: ScheduleRepositoryImpl
) : ViewModel() {

    private val _caseUser = MutableLiveData<CaseUserResponse?>()
    val caseUser: LiveData<CaseUserResponse?> get() = _caseUser

    private val _recentSchedule = MutableLiveData<RecentScheduleResponse?>()
    val recentSchedule: LiveData<RecentScheduleResponse?> get() = _recentSchedule

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Track the individual loading states
    private var caseUserLoaded = false
    private var recentScheduleLoaded = false

    // Update this method to check if both API calls have completed
    private fun checkIfLoadingComplete() {
        if (caseUserLoaded && recentScheduleLoaded) {
            _isLoading.value = false
        }
    }

    fun loadCaseUser() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = caseUserRepository.getCaseUser()
                if (response != null) {
                    _caseUser.value = response
                    caseUserLoaded = true
                    checkIfLoadingComplete() // Check if both are loaded
                } else {
                    _errorMessage.value = "사용자 정보를 불러오지 못했습니다."
                }
            } catch (e: Exception) {
                _errorMessage.value = "에러 발생: ${e.message}"
            }
        }
    }

    fun loadRecentSchedule() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = scheduleRepository.getScheduleRecent()
                if (response != null) {
                    _recentSchedule.value = response
                    recentScheduleLoaded = true
                    checkIfLoadingComplete() // Check if both are loaded
                } else {
                    _errorMessage.value = "일정 정보를 불러오지 못했습니다."
                }
            } catch (e: Exception) {
                _errorMessage.value = "에러 발생: ${e.message}"
            }
        }
    }

    fun loadData() {
        loadCaseUser()
        loadRecentSchedule() // Load both user and schedule data
    }
}
