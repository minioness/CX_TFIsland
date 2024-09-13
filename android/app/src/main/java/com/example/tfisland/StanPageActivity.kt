package com.example.tfisland

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tfisland.databinding.StanPageBinding

class StanPageActivity : AppCompatActivity() {

    private lateinit var binding: StanPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StanPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent에서 artist 데이터 받기
        val artist = intent.getStringExtra("artist") ?: "아티스트"

        // artist 데이터를 텍스트뷰에 적용
        binding.artistTextView.text = "$artist 팬덤의"
        binding.statisticsTextView.text = "오늘 감정 통계"

        // 스타일 적용 (글씨 크기 크게, 볼드체)
        binding.artistTextView.textSize = 24f  // 글씨 크기
        binding.artistTextView.setTypeface(null, Typeface.BOLD)  // 볼드체

        // 팬덤 미션 버튼
        val btnFandomMission = findViewById<Button>(R.id.btn_fandom_mission)
        btnFandomMission.setOnClickListener {
            // MissionPageActivity로 이동
            val intent = Intent(this, MissionPageActivity::class.java)
            startActivity(intent)
        }

        // 순위 아이콘 클릭 시 RankingPageActivity로 이동
        val rankingIcon = findViewById<ImageView>(R.id.ranking_icon)
        rankingIcon.setOnClickListener {
            val intent = Intent(this, RankingPageActivity::class.java)
            startActivity(intent)
        }

        // 팬덤 지도 보기 버튼 클릭 시 MapPageActivity로 이동
        val btnFandomMap = findViewById<Button>(R.id.btn_fandom_map)
        btnFandomMap.setOnClickListener {
            val intent = Intent(this, MapPageActivity::class.java)
            startActivity(intent)
        }

        // 뒤로가기 버튼 클릭 시 이전 페이지로 이동
        val backButton = findViewById<ImageView>(R.id.toolbar_icon)
        backButton.setOnClickListener {
            onBackPressed() // 이전 페이지로 이동
        }
    }
}
