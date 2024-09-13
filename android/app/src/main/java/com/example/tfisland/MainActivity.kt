package com.example.tfisland

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tfisland.databinding.MainPageBinding
import com.example.tfisland.model.network.NetworkModule
import com.example.tfisland.model.repository.CaseUserRepositoryImpl
import com.example.tfisland.model.repository.ScheduleRepositoryImpl
import com.example.tfisland.viewmodel.MainPageViewModel
import com.example.tfisland.viewmodel.factory.MainPageViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainPageBinding
    private val viewModel: MainPageViewModel by viewModels {
        MainPageViewModelFactory(CaseUserRepositoryImpl(NetworkModule.apiService), ScheduleRepositoryImpl(NetworkModule.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initial state: show progress bar, hide content
        binding.mainContent.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        // Load user and schedule data
        viewModel.loadData()

        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.mainContent.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.mainContent.visibility = View.VISIBLE
            }
        }

        // Observe user data and bind it to the UI
        viewModel.caseUser.observe(this) { caseUser ->
            caseUser?.let {
                binding.headerText.text = it.nickname
                binding.subheaderText.text = it.intro
            }
        }

        // Observe recent schedule data and bind it to the UI
        viewModel.recentSchedule.observe(this) { recentSchedule ->
            recentSchedule?.let {
                binding.eventText.text = "D-${it.dDay} ${it.content}"
            }
        }

        // Observe for any errors
        viewModel.errorMessage.observe(this) { message ->
            if (!message.isNullOrEmpty()) {
                showToast(message)
                binding.progressBar.visibility = View.VISIBLE
                binding.mainContent.visibility = View.GONE
            }
        }

        setupUI()

        // Timeout after 10 seconds
        lifecycleScope.launch {
            delay(10000)
            if (viewModel.isLoading.value == true) {
                binding.progressBar.visibility = View.GONE
                binding.mainContent.visibility = View.VISIBLE
                showToast("로딩 시간이 초과되어 메인 화면을 표시합니다.")
            }
        }

        // Set up button listeners
        binding.btnFandomLife.setOnClickListener {
            startActivity(Intent(this, FanPageActivity::class.java))
        }

        binding.btnFandomPlay.setOnClickListener {
            val artist = viewModel.caseUser.value?.artist ?: "디폴트 아티스트" // 서버에서 데이터를 받지 못하면 기본 값 설정
            val intent = Intent(this, StanPageActivity::class.java)
            intent.putExtra("artist", artist)
            startActivity(intent)
        }

        binding.btnDeviceSetting.setOnClickListener {
            startActivity(Intent(this, DeviceSettingPageActivity::class.java)) // 기기 설정 페이지로 이동
        }

        binding.btnBackgroundSetting.setOnClickListener {
            startActivity(Intent(this, BackSettingPageActivity::class.java)) // 배경 설정 페이지로 이동
        }

        binding.profileIcon.setOnClickListener {
            val nickname = viewModel.caseUser.value?.nickname
            val intro = viewModel.caseUser.value?.intro
            val address = viewModel.caseUser.value?.address
            val addressTotal = "${address?.city} ${address?.district} ${address?.street}"
            val intent = Intent(this, InfoPageActivity::class.java)
            intent.putExtra("nickname", nickname)
            intent.putExtra("intro", intro)
            intent.putExtra("address", addressTotal)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        // 메인 페이지로 돌아오면 유저 정보를 다시 로드
        viewModel.loadCaseUser()
        setupUI()
    }

    override fun onResume() {
        super.onResume()

        // 메인 페이지로 돌아오면 유저 정보를 다시 로드
        viewModel.loadCaseUser()
        setupUI()
    }

    override fun onRestart() {
        super.onRestart()

        // 메인 페이지로 돌아오면 유저 정보를 다시 로드
        viewModel.loadCaseUser()
        setupUI()
    }

    // 메인 페이지에서 데이터 로드 후 UI 업데이트
    private fun setupUI() {
        // Observe user data and bind it to the UI
        viewModel.caseUser.observe(this) { caseUser ->
            caseUser?.let {
                // 닉네임과 소개글을 UI에 바인딩
                binding.headerText.text = it.nickname
                binding.subheaderText.text = it.intro
            }
        }

        // Observe recent schedule data and bind it to the UI
        viewModel.recentSchedule.observe(this) { recentSchedule ->
            recentSchedule?.let {
                // 최근 일정 정보를 바인딩
                binding.eventText.text = "D-${it.dDay} ${it.content}"
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
