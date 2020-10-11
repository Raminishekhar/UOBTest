package com.shekhar.uobtest.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.shekhar.uobtest.base.BaseViewModel
import com.shekhar.uobtest.di.DaggerServiceComponent
import com.shekhar.uobtest.model.Server
import com.shekhar.uobtest.model.ServerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ServerListViewModel  : BaseViewModel() {
    val serverListLive = MutableLiveData<List<Server>>()
    @Inject
    lateinit var serverService: ServerService
    private val disposable = CompositeDisposable()

    init {
        DaggerServiceComponent.create().inject(this)
    }

    fun fetchServers(applicationContext:Context) {

        dataLoading.value = true
        disposable.add(
            serverService.getService(applicationContext)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Server>>() {
                    override fun onSuccess(value: List<Server>) {

                        serverListLive.value = value
                        dataLoading.value = false
                        empty.value=false
                    }

                    override fun onError(e: Throwable) {
                        dataLoading.value = false
                        empty.value=true
                    }

                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}