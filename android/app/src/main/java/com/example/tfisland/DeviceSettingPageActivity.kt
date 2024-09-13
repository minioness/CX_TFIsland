package com.example.tfisland

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DeviceSettingPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.device_setting_page)

        val imageView = findViewById<ImageView>(R.id.mission_image_view)
        imageView.setOnClickListener {
            // 뒤로 가기 기능
            onBackPressed()
        }

    }
}