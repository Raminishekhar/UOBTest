package com.shekhar.uobtest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.shekhar.uobtest.base.BaseViewModel
import com.shekhar.uobtest.model.Server

class ServerDetailViewModel  : BaseViewModel() {
    val serverLive = MutableLiveData<Server>()

    fun setServer(server: Server) {
        serverLive.value=server
    }
}