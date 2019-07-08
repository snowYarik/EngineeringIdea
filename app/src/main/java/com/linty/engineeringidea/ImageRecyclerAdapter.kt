package com.linty.engineeringidea

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.bumptech.glide.Glide

class ImageRecyclerAdapter(private var images: List<String>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.image_recycler_include, p0, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val holder = p0 as ImageViewHolder
        Glide.with(context).load(images.get(p1)).into(p0.imageView)

    }

    private class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageButton = view.findViewById(R.id.image)

    }
}