package com.shekhar.uobtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ServerResponse(
    val serversList: List<Server>
)
@Parcelize
data class Server(
    val baseurl: String,
    val country: String,
    val id:String,
    val desc:String
):Parcelable