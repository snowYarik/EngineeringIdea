package com.linty.engineeringidea.links

import com.linty.engineeringidea.Link

interface IVIew {

    fun successLoad(links: List<Link>)
    fun errorLoad(message: String)
}