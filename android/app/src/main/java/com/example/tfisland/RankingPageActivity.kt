package com.example.tfisland

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RankingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_page) // ranking_page.xml 설정

        val imageView = findViewById<ImageView>(R.id.mission_image_view)
        imageView.setOnClickListener {
            // 뒤로 가기 기능
            onBackPressed()
        }
    }
}
