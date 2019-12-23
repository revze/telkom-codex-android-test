package id.revan.topstory.di.modules

import dagger.Module
import dagger.Provides
import id.revan.topstory.data.services.ApiService
import id.revan.topstory.helper.constants.Endpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Endpoint.BASE_URL).build().create(ApiService::class.java)
    }
}