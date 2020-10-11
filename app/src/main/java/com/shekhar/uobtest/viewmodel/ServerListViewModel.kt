package com.shekhar.uobtest.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shekhar.uobtest.R
import com.shekhar.uobtest.base.BaseViewModel
import com.shekhar.uobtest.model.Server
import com.shekhar.uobtest.model.ServerResponse
import com.shekhar.uobtest.utils.getJsonDataFromAsset

class ServerListViewModel  : BaseViewModel() {
    val serverListLive = MutableLiveData<List<Server>>()

    fun fetchServers(applicationContext:Context) {

        dataLoading.value = true
        val jsonFileString = getJsonDataFromAsset(applicationContext, "servers.json")

        val listPersonType = object : TypeToken<ServerResponse>() {}.type
        var serverResp: ServerResponse =  Gson().fromJson(jsonFileString, listPersonType)

        serverListLive.value = serverResp?.serversList;
        dataLoading.value = false
        empty.value=false
    }
}