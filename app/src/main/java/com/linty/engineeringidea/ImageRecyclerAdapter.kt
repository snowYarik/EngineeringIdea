package com.linty.engineeringidea

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView

class ImageRecyclerAdapter(
    val images: List<String>,
    val context: Context,
    val onImageListener: OnImageListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.image_recycler_include, p0, false),
            onImageListener
        )
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val holder = p0 as ImageViewHolder
        Glide.with(context).load(images.get(p1)).centerCrop().into(holder.imageView)
        if (holder.imageView.isFocusable) {
            holder.spinkit.visibility = View.VISIBLE
        } else
            holder.spinkit.visibility = View.GONE

    }


    private class ImageViewHolder(view: View, val onImageListener: OnImageListener) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {
        var imageView: ImageView = view.findViewById(R.id.image)
        var spinkit: SpinKitView = view.findViewById(R.id.spinkit)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            spinkit.visibility = View.VISIBLE
            onImageListener.onImageClick(adapterPosition)
        }


    }


}