package com.example.zcg

import android.app.Application
import com.zcg.core.SectionApi
import com.zcg.core.SectionService
import com.zcg.repository.SectionRepository
import com.zcg.repository.SectionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@HiltAndroidApp
class ZCGApp : Application()

@Module
@InstallIn(SingletonComponent::class)
abstract class SectionRepositoryImplModule {
    @Singleton
    @Binds
    abstract fun bindSectionRepository(
        sectionRepositoryImpl: SectionRepositoryImpl
    ): SectionRepository
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
object SectionRepositoryModule {
    @Singleton
    @Provides
    fun provideSectionRepository(
        @IoDispatcher defaultDispatcher: CoroutineDispatcher,
        sectionService: SectionService
    ) = SectionRepositoryImpl(defaultDispatcher, sectionService)
}

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    @IoDispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Module
@InstallIn(SingletonComponent::class)
object SectionServiceModule {
    @Provides
    fun provideSectionService(sectionApi: SectionApi): SectionService {
        return SectionService(sectionApi)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object CertificatePinnerModule {
    @Provides
    fun provideCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add("jsonkeeper.com", "sha256/CwbLBzTEaZp1Lr6T/55DBDt5wixd1XjvAQNp6KRybPM=")
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientModule {
    @Provides
    fun provideOkHttpClient(
        certificatePinner: CertificatePinner
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .hostnameVerifier { _, _ ->
                true
            }
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object SectionApiModule {
    @Singleton
    @Provides
    fun provideSectionApi(
        okHttpClient: OkHttpClient
    ): SectionApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SectionApi::class.java)
    }
}
