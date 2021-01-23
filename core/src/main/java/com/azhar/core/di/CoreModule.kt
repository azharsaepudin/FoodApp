package com.azhar.core.di

import androidx.room.Room
import com.azhar.core.data.FoodAppRepository
import com.azhar.core.data.source.local.LocalDataSource
import com.azhar.core.data.source.local.room.FoodAppDatabase
import com.azhar.core.data.source.remote.RemoteDataSource
import com.azhar.core.data.source.remote.network.ApiService
import com.azhar.core.domain.repository.IFoodAppRepository
import com.azhar.core.utils.AppExecutors
import com.google.gson.GsonBuilder
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {

        val hostname = "azharsaepudin.github.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/xlDAST56PmiT3SR0WdFOR3dghwJrQ8yXx6JLSqTIRpk=")
            .add(hostname, "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=")
            .add(hostname, "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(123, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://azharsaepudin.github.io/FootBallPlayer/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(get())
                .build()
        retrofit.create(ApiService::class.java)
    }

}

val databaseModule = module {
    factory { get<FoodAppDatabase>().foodAppDao() }

    single {

        val passphrase: ByteArray = SQLiteDatabase.getBytes("azhar".toCharArray())

        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
                androidContext(),
                FoodAppDatabase::class.java, "food.db"
        ).fallbackToDestructiveMigration()
                .openHelperFactory(factory).build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }

    single<IFoodAppRepository> {
        FoodAppRepository(
                get(),
                get(),
                get()
        )
    }
}