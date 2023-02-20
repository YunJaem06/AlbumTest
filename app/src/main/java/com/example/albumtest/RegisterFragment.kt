package com.example.albumtest

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.example.albumtest.databinding.FragmentRegisterBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::bind, R.layout.fragment_register) {

    var imageItem = mutableListOf<Uri>()
    lateinit var registerAdapter : RegisterAdapter
    lateinit var fbStorage: StorageReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fbStorage = FirebaseStorage.getInstance().reference

        registerAdapter = RegisterAdapter(imageItem, requireActivity())
        binding.registerRvGallery.adapter = registerAdapter

        imageClick()
    }

    fun imageClick() {
        binding.registerBtnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            loadImage.launch(intent)
            }
    }

    // 이미지 가져오기
    val loadImage : ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {

            var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            var imgFileName = "IMAGE_" + timeStamp + "_.png"

            // 멀티 선택
            if (it.data!!.clipData != null) {
                val count = it.data!!.clipData!!.itemCount

                for (index in 0 until count) {
                    val imageUri = it.data!!.clipData!!.getItemAt(index).uri
                    fbStorage.child("Image").child(imgFileName)
                    imageItem.add(imageUri)
                }
                // 하나 선택
            } else {
                val imageUri = it.data!!.data

                // 파베에 전달하고 파베 uri주소 받아서 전달
                val pathReference = fbStorage.child("Image").child(imgFileName)
                pathReference.putFile(imageUri!!).addOnSuccessListener {
                    val result = it.metadata!!.reference!!.downloadUrl
                    result.addOnSuccessListener {
                        var galleryUri = it
                        imageItem.add(galleryUri)
                        Log.d("imageUri", "$galleryUri")
                        registerAdapter.notifyDataSetChanged()
                    }
                }
            }
            registerAdapter.notifyDataSetChanged()
        }

    }
}