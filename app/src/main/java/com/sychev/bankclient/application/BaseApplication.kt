package com.sychev.bankclient.application

import android.app.Application
import com.sychev.bankclient.utils.DataStoreProvider
import com.sychev.shared.backend.BackendStorageManagerImpl
import com.sychev.shared.backend.provideBackendStorageManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    private val backendStorageManagerApi =
        (provideBackendStorageManager() as BackendStorageManagerImpl)

    override fun onCreate() {
        super.onCreate()
        DataStoreProvider.initialize(applicationContext)
        backendStorageManagerApi.providerAppContext(applicationContext)
    }

}