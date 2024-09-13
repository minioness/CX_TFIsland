package com.example.tfisland

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tfisland.databinding.InfoPageBinding
import com.example.tfisland.model.network.NetworkModule
import com.example.tfisland.model.repository.ArtistRepositoryImpl
import com.example.tfisland.model.repository.CaseUserRepositoryImpl
import com.example.tfisland.viewmodel.InfoPageViewModel
import com.example.tfisland.viewmodel.factory.InfoPageViewModelFactory

class InfoPageActivity : AppCompatActivity() {

    private lateinit var binding: InfoPageBinding
    private val PICK_IMAGE_REQUEST = 1
    private var selectedArtistId: Long? = null // 선택된 아티스트 ID 저장

    // ViewModel을 by viewModels로 초기화
    private val viewModel: InfoPageViewModel by viewModels {
        InfoPageViewModelFactory(
            ArtistRepositoryImpl(NetworkModule.apiService),
            CaseUserRepositoryImpl(NetworkModule.apiService)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InfoPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아티스트 목록 로드
        viewModel.loadArtists()

        // Intent에서 전달된 데이터 받기
        val intro = intent.getStringExtra("intro")
        val address = intent.getStringExtra("address")
        val nickname = intent.getStringExtra("nickname")

        // 텍스트뷰에 데이터 적용
        binding.nicknameInput.setText(nickname)
        binding.introInput.setText(intro)
        binding.addressInput.setText(address)

        // Spinner 및 기타 UI 설정
        setupArtistDropdown()

        // 갤러리에서 이미지 선택
        binding.uploadImageButton.setOnClickListener { openGallery() }

        // 변경 사항 저장 버튼 클릭 시 유저 정보 업데이트
        binding.registerButton.setOnClickListener { submitCaseUserInfo() }

        // 뒤로가기 버튼
        binding.toolbarIcon.setOnClickListener { onBackPressed() }
    }

    private fun submitCaseUserInfo() {
        val nickname = binding.nicknameInput.text.toString()
        val address = binding.addressInput.text.toString()
        val intro = binding.introInput.text.toString()

        if (selectedArtistId == null) {
            Toast.makeText(this, "아티스트를 선택하세요.", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.updateUserInfo(selectedArtistId!!, nickname, intro, address).observe(this) { isSuccess ->
            if (isSuccess) {
                // 성공적으로 업데이트되면 메인 화면으로 이동
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish() // 현재 액티비티 종료
            } else {
                // 실패하면 에러 메시지 표시
                Toast.makeText(this, "업데이트에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun setupArtistDropdown() {
        viewModel.artists.observe(this) { artists ->
            if (artists.isNotEmpty()) {
                // Spinner에 아티스트 목록 설정
                val artistNames = artists.map { it.name }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, artistNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.artistSpinner.adapter = adapter

                // Spinner 선택 이벤트 처리
                binding.artistSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedArtistId = artists[position].artistId // 선택된 아티스트의 ID 저장
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        selectedArtistId = null
                    }
                }
            }
        }

        // 오류 처리
        viewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            selectedImageUri?.let {
                try {
                    // 선택된 이미지를 ImageView에 설정
                    val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                    binding.profileImage.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "이미지를 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
