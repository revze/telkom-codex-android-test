package id.revan.topstory.data.repository

import id.revan.topstory.data.model.StoryDetail
import id.revan.topstory.data.services.ApiService
import id.revan.topstory.helper.constants.StatusCode
import java.io.IOException
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(private val apiService: ApiService) : StoryRepository {
    override suspend fun getTopStory(): Output<IntArray> {
        return try {
            val result = apiService.getTopStory()
            Output.Success(result)
        } catch (e: IOException) {
            Output.Error(StatusCode.NETWORK_ERROR, "")
        } catch (e: Exception) {
            Output.Error(StatusCode.GENERAL_ERROR, "")
        }
    }

    override suspend fun getStoryDetail(id: Int): Output<StoryDetail> {
        return try {
            val result = apiService.getStoryDetail(id)
            Output.Success(result)
        } catch (e: IOException) {
            Output.Error(StatusCode.NETWORK_ERROR, "")
        } catch (e: Exception) {
            Output.Error(StatusCode.GENERAL_ERROR, "")
        }
    }
}