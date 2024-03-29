package id.revan.topstory.data.repository

import id.revan.topstory.data.model.CommentDetail
import id.revan.topstory.data.model.StoryDetail

interface StoryRepository {
    suspend fun getTopStory(): Output<IntArray>

    suspend fun getStoryDetail(id: Int): Output<StoryDetail>

    suspend fun getCommentDetail(id: Int): Output<CommentDetail>
}