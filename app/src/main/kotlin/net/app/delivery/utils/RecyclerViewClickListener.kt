package net.app.delivery.utils

import net.app.delivery.model.LocationDetails

interface RecyclerViewClickListener {
    fun onClick(location :LocationDetails, title : String , imageUrl : String)
}