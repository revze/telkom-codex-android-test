package id.revan.topstory.data.model

import com.google.gson.annotations.SerializedName

data class CommentDetail(
    @SerializedName("text")
    val text: String?
)