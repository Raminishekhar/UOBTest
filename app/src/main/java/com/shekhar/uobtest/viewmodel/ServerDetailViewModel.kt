package com.shekhar.uobtest.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shekhar.uobtest.base.BaseViewModel
import com.shekhar.uobtest.model.Server
import com.shekhar.uobtest.model.ServerResponse
import com.shekhar.uobtest.utils.getJsonDataFromAsset

class ServerDetailViewModel  : BaseViewModel() {
    val serverLive = MutableLiveData<Server>()

    fun setServer(server: Server) {
        serverLive.value=server
    }
}