package com.linty.engineeringidea.listener

/**
 * OnLinkListener is the interface for link click listening
 */
interface OnLinkListener {
    /**
     * on link click method
     * @param position position of the view in recyclerview
     */
    fun onLinkClick(position: Int)
}