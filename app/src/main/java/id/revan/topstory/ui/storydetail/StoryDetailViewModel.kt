package id.revan.topstory.ui.storydetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.revan.topstory.data.repository.Output
import id.revan.topstory.data.repository.StoryRepository
import id.revan.topstory.data.state.StoryDetailState
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoryDetailViewModel @Inject constructor(private val repository: StoryRepository) :
    ViewModel() {
    val storyDetailState = MutableLiveData<StoryDetailState>()

    fun getStoryDetail(id: Int) {
        storyDetailState.value = StoryDetailState(isLoading = true)

        viewModelScope.launch {
            val result = repository.getStoryDetail(id)

            when (result) {
                is Output.Success -> {
                    storyDetailState.postValue(StoryDetailState(storyDetail = result.output))
                }
                is Output.Error -> {
                    storyDetailState.postValue(StoryDetailState(errorCode = result.code))
                }
            }
        }
    }
}