package id.revan.topstory.ui.topstory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.revan.topstory.data.model.StoryDetail
import id.revan.topstory.data.repository.Output
import id.revan.topstory.data.repository.StoryRepository
import id.revan.topstory.data.state.TopStoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: StoryRepository) :
    ViewModel() {
    val topStoryState = MutableLiveData<TopStoryState>()

    fun getTopStory() {
        topStoryState.value = TopStoryState(isLoading = true)

        viewModelScope.launch {
            val result = repository.getTopStory()

            when (result) {
                is Output.Success -> {
                    topStoryState.postValue(
                        TopStoryState(
                            stories = result.output
                        )
                    )
                }
                is Output.Error -> {
                    topStoryState.postValue(TopStoryState(errorCode = result.code))
                }
            }
        }
    }
}