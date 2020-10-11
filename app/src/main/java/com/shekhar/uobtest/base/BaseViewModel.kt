package com.shekhar.uobtest.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val empty = MutableLiveData<Boolean>()

    val dataLoading = MutableLiveData<Boolean>()

    val toastMessage = MutableLiveData<String>()
}