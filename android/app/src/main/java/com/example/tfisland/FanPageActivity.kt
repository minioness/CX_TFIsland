package com.example.tfisland

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tfisland.databinding.FanPageBinding
import com.example.tfisland.model.data.MemoryDecorator
import com.example.tfisland.model.network.NetworkModule
import com.example.tfisland.model.repository.MemoryRepositoryImpl
import com.example.tfisland.model.repository.ScheduleRepositoryImpl
import com.example.tfisland.viewmodel.FanPageViewModel
import com.example.tfisland.viewmodel.factory.FanPageViewModelFactory
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime

class FanPageActivity : AppCompatActivity() {

    private lateinit var binding: FanPageBinding

    private val viewModel: FanPageViewModel by viewModels {
        FanPageViewModelFactory(
            ScheduleRepositoryImpl(NetworkModule.apiService),
            MemoryRepositoryImpl(NetworkModule.apiService)
        )
    }

    private lateinit var calendarView: MaterialCalendarView
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false
    private val audioFilePath: String by lazy {
        externalCacheDir?.absolutePath + "/audio_record.3gp"
    }

    // ImageView 선언
    private lateinit var micButton: ImageView
    private lateinit var backButton: ImageView // 뒤로가기 버튼 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FanPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calendarView = findViewById(R.id.calendarView)

        val toggleScheduleCalendarButton = findViewById<Button>(R.id.toggle_schedule_calendar)
        val toggleEmotionCalendarButton = findViewById<Button>(R.id.toggle_emotion_calendar)

        // 기본으로 일정 달력 로드
        loadScheduleCalendar()

        // 일정 달력 버튼 클릭 리스너
        toggleScheduleCalendarButton.setOnClickListener {
            loadScheduleCalendar() // 일정 달력 로드
        }

        // 감정 달력 버튼 클릭 리스너
        toggleEmotionCalendarButton.setOnClickListener {
            loadEmotionCalendar() // 감정 달력 로드
        }

        micButton = findViewById(R.id.mic_button) // 아이콘을 설정할 ImageView
        backButton = findViewById(R.id.toolbar_icon) // 뒤로가기 버튼

        // 뒤로가기 버튼 클릭 시 이전 페이지로 이동
        backButton.setOnClickListener {
            onBackPressed() // 전 페이지로 이동
        }

        micButton.setOnClickListener {
            if (checkAudioPermission()) {
                if (isRecording) {
                    stopRecording()
                } else {
                    startRecording()
                }
            } else {
                requestAudioPermission()
            }
        }

        binding.archiveButton.setOnClickListener {
            startActivity(Intent(this, ArchivePageActivity::class.java)) // 배경 설정 페이지로 이동
        }
    }

    // 일정 달력을 로드하는 함수
    private fun loadScheduleCalendar() {
        calendarView.removeDecorators() // 기존 데코레이터 제거
        viewModel.loadSchedules()
        viewModel.schedule.observe(this) { schedules ->
            schedules?.let {
                val dates = it.map { scheduleItem ->
                    val localDate = LocalDateTime.parse(scheduleItem.scheduledAt)
                    val color = android.graphics.Color.parseColor("#F185B0")
                    localDate?.let{
                        MemoryDecorator(CalendarDay.from(localDate.year, localDate.monthValue, localDate.dayOfMonth), color)
                    }
                }
                dates.forEach { calendarView.addDecorator(it) }
            }
        }
    }

    // 감정 달력을 로드하는 함수
    private fun loadEmotionCalendar() {
        calendarView.removeDecorators() // 기존 데코레이터 제거
        viewModel.loadMemory()
        viewModel.memory.observe(this) { memories ->
            memories?.let {
                val memoryDates = memories.map { memory ->
                    val localDate = LocalDate.parse(memory.memoryDate)
                    val color = android.graphics.Color.parseColor("#"+memory.color)
                    localDate?.let {
                        MemoryDecorator(CalendarDay.from(localDate.year, localDate.monthValue, localDate.dayOfMonth), color)
                    }
                }
                memoryDates.forEach { calendarView.addDecorator(it) }
            }
        }
    }

    private fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(audioFilePath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
                start()
                isRecording = true
                micButton.setImageResource(R.drawable.ic_mic_next) // 녹음 시작 시 아이콘 변경
                Toast.makeText(this@FanPageActivity, "녹음이 시작되었습니다.", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Log.e("FanPageActivity", "녹음 준비에 실패했습니다: ${e.message}")
            }
        }
    }

    private fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        isRecording = false
        micButton.setImageResource(R.drawable.ic_mic_prev) // 녹음 중지 시 아이콘 변경
        Toast.makeText(this, "녹음이 완료되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun checkAudioPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestAudioPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 200)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 200) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                startRecording()
            } else {
                Toast.makeText(this, "녹음 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
