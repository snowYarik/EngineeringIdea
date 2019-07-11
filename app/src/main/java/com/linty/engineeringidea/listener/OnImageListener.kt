package com.linty.engineeringidea.listener
/**
 * OnImageListener is the interface for image click listening
 */
interface OnImageListener {
    /**
     * on image click method
     * @param position position of the view in recyclerview
     */
    fun onImageClick(position: Int)
}