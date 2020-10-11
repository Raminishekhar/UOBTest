package com.shekhar.uobtest.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shekhar.uobtest.utils.getJsonDataFromAsset
import io.reactivex.Single

class ServerService {

    fun getService(applicationContext: Context): Single<List<Server>> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "servers.json")
        val listPersonType = object : TypeToken<ServerResponse>() {}.type
        var serverResp: ServerResponse =  Gson().fromJson(jsonFileString, listPersonType)
       return  Single.just(serverResp?.serversList);
    }
}