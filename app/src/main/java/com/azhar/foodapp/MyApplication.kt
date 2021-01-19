package com.azhar.foodapp

import android.app.Application
import com.azhar.core.di.databaseModule
import com.azhar.core.di.networkModule
import com.azhar.core.di.repositoryModule
import com.azhar.foodapp.di.useCaseModule
import com.azhar.foodapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                    listOf(
                            databaseModule,
                            networkModule,
                            repositoryModule,
                            useCaseModule,
                            viewModelModule
                    )
            )
        }
    }

}