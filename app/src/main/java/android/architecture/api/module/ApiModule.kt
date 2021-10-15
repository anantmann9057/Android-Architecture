package android.architecture.api.module

import android.app.Application
import android.architecture.api.service.ApiService
import android.architecture.api.service.ImageApiService
import android.architecture.database.PicsDatabase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun interceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun OkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor())
        .build()

    @Provides
    @Singleton
    @Named("text_api")
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @Named("text_api")
    fun provideCannabisApi(@Named("text_api") retrofit: Retrofit): ApiService =
        providesRetrofit()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    @Named("images_api")
    fun providesImagesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ImageApiService.IMAGE_BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @Named("images_api")
    fun provideImages(@Named("images_api") retrofit: Retrofit): ImageApiService =
        providesImagesRetrofit()
            .create(ImageApiService::class.java)

    @Provides
    @Singleton
    @Named("pics_database")
    fun provideDatabase(app: Application): PicsDatabase =
        Room.databaseBuilder(app, PicsDatabase::class.java, "pics_database")
            .fallbackToDestructiveMigration()
            .build()

}