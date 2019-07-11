package com.linty.engineeringidea.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.linty.engineeringidea.listener.OnLinkListener
import com.linty.engineeringidea.R
import com.linty.engineeringidea.model.Link

/**
 * Class adapter for image RecyclerView
 * @param context the context
 * @param links the list of images
 * @param linkListener the image click listener
 * */
class LinkRecyclerAdapter(val context: Context, var links: List<Link>, val linkListener: OnLinkListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return LinkViewHolder(
            LayoutInflater.from(context).inflate(R.layout.link_recycler_include, p0, false),
            linkListener
        )
    }

    /**
     * getItemCount is a method witch return a count of the elements in the recyclerview
     * @return count of the elements
     */
    override fun getItemCount(): Int {
        return links.size
    }

    /**
     * onBindViewHolder is a method witch bind view elements
     * @param p0 viewholder for binding
     * @param p1 index of the current element
     */
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val holder = p0 as LinkViewHolder
        holder.linkTV.text = links[p1].link
    }

    /**
     * Nested Class ViewHolder for LinkRecyclerAdapter
     * */
    private class LinkViewHolder(view: View, private val linkListener: OnLinkListener) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val linkTV: TextView = view.findViewById(R.id.link_text)

        init {
            linkTV.setOnClickListener(this)
        }

        /**
         * click method
         */
        override fun onClick(v: View?) {
            linkListener.onLinkClick(adapterPosition)
        }

    }
}