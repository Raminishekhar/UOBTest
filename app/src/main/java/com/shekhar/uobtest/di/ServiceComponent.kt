package com.shekhar.uobtest.di

import com.shekhar.uobtest.model.ServerService
import com.shekhar.uobtest.viewmodel.ServerListViewModel
import dagger.Component


@Component(modules = [ServiceModule::class])
interface ServiceComponent {

    fun inject(viewModel: ServerListViewModel)

}