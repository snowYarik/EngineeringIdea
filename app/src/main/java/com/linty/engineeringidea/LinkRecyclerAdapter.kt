package com.linty.engineeringidea

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class LinkRecyclerAdapter(val context: Context, val links: List<Link>, val linkListener: OnLinkListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return LinkViewHolder(
            LayoutInflater.from(context).inflate(R.layout.link_recycler_include, p0, false),
            linkListener
        )
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val holder = p0 as LinkViewHolder
        holder.linkTV.text = links[p1].link
    }

    private class LinkViewHolder(view: View, private val linkListener: OnLinkListener) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val linkTV: TextView = view.findViewById(R.id.link_text)

        init {
            linkTV.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            linkListener.onLinkClick(adapterPosition)
        }

    }
}