package id.revan.topstory.data.services

import id.revan.topstory.data.model.CommentDetail
import id.revan.topstory.data.model.StoryDetail
import id.revan.topstory.helper.constants.Endpoint
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Endpoint.TOP_STORY)
    suspend fun getTopStory(): IntArray

    @GET(Endpoint.STORY_DETAIL)
    suspend fun getStoryDetail(@Path(Endpoint.STORY_ID) id: Int): StoryDetail

    @GET(Endpoint.COMMENT_DETAIL)
    suspend fun getCommentDetail(@Path(Endpoint.COMMENT_ID) id: Int): CommentDetail
}