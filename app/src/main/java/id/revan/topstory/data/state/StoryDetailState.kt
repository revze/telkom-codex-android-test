package id.revan.topstory.data.state

import id.revan.topstory.data.model.StoryDetail
import id.revan.topstory.helper.constants.StatusCode

data class StoryDetailState(
    val isLoading: Boolean = false,
    val errorCode: Int = StatusCode.NO_ERROR,
    val storyDetail: StoryDetail? = null,
    val comments: List<String> = mutableListOf()
)