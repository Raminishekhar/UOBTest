package com.shekhar.uobtest

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shekhar.uobtest.model.Server
import com.shekhar.uobtest.model.ServerService
import com.shekhar.uobtest.viewmodel.ServerListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class ServerListViewModelTest {

    private var testSingle: Single<List<Server>>? = null

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var serverService: ServerService

    @InjectMocks
    var serverListViewModel= ServerListViewModel()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setUpRxSchedulers(){
        var immediate= object: Scheduler(){

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor{it.run()})
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler->immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler->immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler->immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler->immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler-> immediate }
    }

    @Test
    fun getServersSuccess(){
        val server= Server("http://test.com","Singapore","sg","singapore")
        val serverList= arrayListOf(server)
        testSingle= Single.just(serverList)
        val ctx = mock(Context::class.java)

        Mockito.`when`(serverService.getService(ctx)).thenReturn(testSingle)

        serverListViewModel.fetchServers(ctx)

        Assert.assertEquals(1,serverListViewModel.serverListLive.value?.size)
        Assert.assertEquals(false,serverListViewModel.dataLoading.value)
        Assert.assertEquals(false,serverListViewModel.empty.value)
    }

    @Test
    fun getServersFail(){
        testSingle = Single.error(Throwable())
        val ctx = mock(Context::class.java)
        Mockito.`when`(serverService.getService(ctx)).thenReturn(testSingle)

        serverListViewModel.fetchServers(ctx)
        Assert.assertEquals(null,serverListViewModel.serverListLive.value?.size)
        Assert.assertEquals(false,serverListViewModel.dataLoading.value)
        Assert.assertEquals(true,serverListViewModel.empty.value)
    }

}