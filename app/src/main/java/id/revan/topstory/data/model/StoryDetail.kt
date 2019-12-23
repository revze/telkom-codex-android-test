package id.revan.topstory.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryDetail(
    @SerializedName("title")
    val title: String,

    @SerializedName("by")
    val author: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("time")
    val time: Long,

    @SerializedName("kids")
    val comments: List<Int>?
) : Parcelable