package com.example.albumtest

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.albumtest.databinding.ItemRegisterGalleryImageBinding

class RegisterAdapter(var itemList: MutableList<Uri>, var context: Context) : RecyclerView.Adapter<RegisterAdapter.ViewHolder>() {

//    lateinit var itemList : MutableList<Uri>
//    lateinit var context : Context
//
//    constructor(itemList : MutableList<Uri>, context: Context) : this() {
//        this.itemList = itemList
//        this.context = context
//    }

    inner class ViewHolder(val binding: ItemRegisterGalleryImageBinding) : RecyclerView.ViewHolder(binding.root) {
        val galleryView = binding.itemRegisterGalleryImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRegisterGalleryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(itemList[position])
            .into(holder.galleryView)
    }
}