package com.antarikshc.news.di

import android.content.Context
import com.antarikshc.news.data.local.NewsDatabase
import com.antarikshc.news.data.remote.NewsService
import com.antarikshc.news.models.NewsResponse
import com.antarikshc.news.utils.NewsResponseDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NewsModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(NewsResponse::class.java, NewsResponseDeserializer())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    }

    @Provides
    fun provideNewsService(gson: Gson): NewsService {
        return Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    fun provideDatabase(context: Context): NewsDatabase {
        return NewsDatabase.build(context.applicationContext)
    }


}