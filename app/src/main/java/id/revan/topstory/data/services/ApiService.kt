package id.revan.topstory.data.services

import id.revan.topstory.data.model.StoryDetail
import id.revan.topstory.helper.constants.Endpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Endpoint.TOP_STORY)
    suspend fun getTopStory(): IntArray

    @GET(Endpoint.STORY_DETAIL)
    suspend fun getStoryDetail(@Path(Endpoint.STORY_ID) storyId: Int): StoryDetail

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Endpoint.BASE_URL).build().create(ApiService::class.java)
        }
    }
}