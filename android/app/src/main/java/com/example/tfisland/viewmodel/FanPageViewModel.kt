package com.example.tfisland.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfisland.model.data.ScheduleItem
import com.example.tfisland.model.dto.ArtistScheduleResponse
import com.example.tfisland.model.dto.CaseUserScheduleResponse
import com.example.tfisland.model.dto.MemoryResponse
import com.example.tfisland.model.repository.MemoryRepositoryImpl
import com.example.tfisland.model.repository.ScheduleRepositoryImpl
import kotlinx.coroutines.launch

class FanPageViewModel(
    private val scheduleRepository: ScheduleRepositoryImpl,
    private val memoryRepository: MemoryRepositoryImpl
) : ViewModel() {

    private val _schedule = MutableLiveData<List<ScheduleItem>>()
    val schedule: LiveData<List<ScheduleItem>> get() = _schedule

    private val _memory = MutableLiveData<List<MemoryResponse>>()
    val memory: LiveData<List<MemoryResponse>> get() = _memory

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // 일정 데이터 로드
    fun loadSchedules() {
        viewModelScope.launch {
            try {
                val scheduleResponse = scheduleRepository.getSchedule() // Repository에서 데이터 가져옴
                scheduleResponse?.let {
                    // 받은 데이터를 병합하고 정렬하여 처리
                    val combinedSchedules = mergeAndSortSchedules(
                        it.caseUserScheduleResponseList,
                        it.artistScheduleResponseList
                    )
                    _schedule.postValue(combinedSchedules) // LiveData에 값 설정
                } ?: run {
                    _errorMessage.postValue("일정을 불러오지 못했습니다.")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("일정 로딩 실패: ${e.message}")
            }
        }
    }

    // 감정 데이터 로드
    fun loadMemory() {
        viewModelScope.launch {
            try {
                val memoryResponse = memoryRepository.getMemory()
                memoryResponse?.let {
                    _memory.postValue(it)
                } ?: run {
                    _errorMessage.postValue("감정 데이터를 불러오지 못했습니다.")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("감정 데이터 로딩 실패: ${e.message}")
            }
        }
    }

    // 개인 일정과 아티스트 일정을 병합하고 scheduledAt 기준으로 정렬하는 메서드
    private fun mergeAndSortSchedules(
        caseUserSchedules: List<CaseUserScheduleResponse>,
        artistSchedules: List<ArtistScheduleResponse>
    ): List<ScheduleItem> {
        val caseItems = caseUserSchedules.map { ScheduleItem.UserSchedule(it) }
        val artistItems = artistSchedules.map { ScheduleItem.ArtistSchedule(it) }
        return (caseItems + artistItems).sortedBy { it.scheduledAt }
    }
}
