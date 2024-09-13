package com.example.tfisland.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfisland.model.dto.ArtistResponse
import com.example.tfisland.model.dto.CaseUserUpdateRequest
import com.example.tfisland.model.repository.ArtistRepositoryImpl
import com.example.tfisland.model.repository.CaseUserRepositoryImpl
import kotlinx.coroutines.launch

class InfoPageViewModel(
    private val artistRepository: ArtistRepositoryImpl,
    private val caseUserRepository: CaseUserRepositoryImpl
) : ViewModel() {

    private val _artists = MutableLiveData<List<ArtistResponse>>()
    val artists: LiveData<List<ArtistResponse>> get() = _artists

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loadArtists() {
        viewModelScope.launch {
            try {
                val artistResponse = artistRepository.getArtist() // Repository에서 데이터 가져옴
                artistResponse?.let {
                    _artists.postValue(it) // LiveData에 값 설정
                } ?: run {
                    _errorMessage.postValue("아티스트 목록을 불러오지 못했습니다.")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("아티스트 목록 로딩 실패: ${e.message}")
            }
        }
    }

    fun updateUserInfo(artistId: Long, nickname: String, intro: String, address: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                // 유저 정보 업데이트 네트워크 요청
                val request = CaseUserUpdateRequest(artistId, nickname, intro, address)
                caseUserRepository.patchCaseUser(request)
                result.postValue(true)  // 성공 시 true 반환
            } catch (e: Exception) {
                result.postValue(false)  // 실패 시 false 반환
            }
        }
        return result
    }
}
