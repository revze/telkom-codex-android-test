package id.revan.topstory.ui.topstory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import id.revan.topstory.R
import id.revan.topstory.data.model.StoryDetail
import id.revan.topstory.data.state.TopStoryState
import id.revan.topstory.di.Injector
import id.revan.topstory.helper.constants.StatusCode
import id.revan.topstory.shared.StoryItem
import id.revan.topstory.shared.extensions.hide
import id.revan.topstory.shared.extensions.show
import id.revan.topstory.ui.base.BaseViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val STORY = "story"
        const val LAST_CLICKED = "last_clicked"
    }

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<MainViewModel>
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var story: StoryDetail? = null
    private var lastClicked: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        story = intent.getParcelableExtra(STORY)
        lastClicked = intent.getStringExtra(LAST_CLICKED)

        Injector.getApp().inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.topStoryState.observe(this, topStoryStateObserver)

        rv_story.layoutManager = GridLayoutManager(this, 2)
        rv_story.adapter = adapter

        btn_try_again.setOnClickListener {
            viewModel.getTopStory()
        }

        viewModel.getTopStory()
    }

    private val topStoryStateObserver = Observer<TopStoryState> {
        if (it.isLoading) {
            progress_bar.show()
            layout_error.hide()
            layout_story_list.hide()
            return@Observer
        }
        if (it.errorCode != StatusCode.NO_ERROR) {
            progress_bar.hide()
            layout_error.show()
            tv_error_message.text =
                if (it.errorCode == StatusCode.GENERAL_ERROR) getString(R.string.general_error_message) else getString(
                    R.string.network_error_message
                )
            layout_story_list.hide()
            return@Observer
        }

        progress_bar.hide()
        layout_error.hide()
        if (story != null) {
            layout_favorite_story.show()
            tv_story_title.text = story?.title
            tv_story_last_clicked.text = lastClicked
        } else {
            layout_favorite_story.hide()
        }
        it.stories.map {
            adapter.add(StoryItem(it))
        }
        layout_story_list.show()
    }
}
