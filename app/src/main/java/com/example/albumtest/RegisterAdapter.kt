package com.example.albumtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtest.databinding.ItemRegisterGalleryImageBinding

class RegisterAdapter(val itemList : MutableList<String>) : RecyclerView.Adapter<RegisterAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRegisterGalleryImageBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRegisterGalleryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }
}