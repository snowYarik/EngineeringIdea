package com.linty.engineeringidea.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView
import com.linty.engineeringidea.listener.OnImageListener
import com.linty.engineeringidea.R

/**
 * Class adapter for image RecyclerView
 * @param images the list of images
 * @param context the context
 * @param onImageListener the image click listener
 * */
class ImageRecyclerAdapter(
    private val images: List<String>,
    private val context: Context,
    private val onImageListener: OnImageListener
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

    /**
     * Nested Class ViewHolder for ImageRecyclerAdapter
     * */
    private class ImageViewHolder(view: View, val onImageListener: OnImageListener) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {
        var imageView: ImageView = view.findViewById(R.id.image)
        var spinkit: SpinKitView = view.findViewById(R.id.spinkit)

        init {
            view.setOnClickListener(this)
        }

        /**
         * image onClick  method
         * */
        override fun onClick(v: View?) {
            spinkit.visibility = View.VISIBLE
            onImageListener.onImageClick(adapterPosition)
        }


    }


}