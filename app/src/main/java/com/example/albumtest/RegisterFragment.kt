package com.example.albumtest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import com.example.albumtest.databinding.FragmentRegisterBinding
import java.text.SimpleDateFormat
import java.util.*

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::bind, R.layout.fragment_register) {

    var selectImage : Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun imageClick() {
        binding.registerBtnGallery.setOnClickListener {
            if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                loadImage.launch(intent)
            }
        }
    }

    // 이미지 가져오기
    val loadImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {

            //값 담기
            selectImage = it.data?.data

            var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            var imgFileName = "IMAGE_" + timeStamp + "_.png"
        }

    }
}