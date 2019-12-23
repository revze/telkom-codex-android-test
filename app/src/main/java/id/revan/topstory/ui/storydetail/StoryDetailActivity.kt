package id.revan.topstory.ui.storydetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import id.revan.topstory.R
import id.revan.topstory.data.model.Comment
import id.revan.topstory.data.model.StoryDetail
import id.revan.topstory.data.state.StoryDetailState
import id.revan.topstory.di.Injector
import id.revan.topstory.helper.DateTimeHelper
import id.revan.topstory.helper.constants.StatusCode
import id.revan.topstory.shared.extensions.hide
import id.revan.topstory.shared.extensions.show
import id.revan.topstory.shared.view.CommentItem
import id.revan.topstory.ui.base.BaseViewModelFactory
import id.revan.topstory.ui.topstory.MainActivity
import kotlinx.android.synthetic.main.activity_story_detail.*
import kotlinx.android.synthetic.main.content_story_detail.*
import kotlinx.android.synthetic.main.layout_error.*
import javax.inject.Inject

class StoryDetailActivity : AppCompatActivity() {

    companion object {
        const val ID = "id"
    }

    private lateinit var viewModel: StoryDetailViewModel

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<StoryDetailViewModel>
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var storyDetail: StoryDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)

        Injector.getApp().inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(StoryDetailViewModel::class.java)
        viewModel.storyDetailState.observe(this, storyDetailStateObserver)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(ID, 0)

        iv_favorite.setOnClickListener {
            Intent(this, MainActivity::class.java)
                .apply {
                    putExtra(MainActivity.STORY, storyDetail)
                    putExtra(MainActivity.LAST_CLICKED, DateTimeHelper.getLastTime())
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                }.also {
                    startActivity(it)
                    finish()
                }
        }

        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = adapter
        rv_comment.isNestedScrollingEnabled = false

        btn_try_again.setOnClickListener {
            viewModel.getStoryDetail(id)
        }

        viewModel.getStoryDetail(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val storyDetailStateObserver = Observer<StoryDetailState> {
        if (it.isLoading) {
            progress_bar.show()
            layout_error.hide()
            sv_story_detail.hide()
            return@Observer
        }
        if (it.errorCode != StatusCode.NO_ERROR) {
            progress_bar.hide()
            layout_error.show()
            tv_error_message.text =
                if (it.errorCode == StatusCode.GENERAL_ERROR) getString(R.string.general_error_message) else getString(
                    R.string.network_error_message
                )
            sv_story_detail.hide()
            return@Observer
        }
        val detail = it.storyDetail
        storyDetail = detail

        progress_bar.hide()
        layout_error.hide()
        sv_story_detail.show()
        if (detail != null) {
            tv_title.text = detail.title
            tv_author.text = "by ${detail.author}"
            tv_date.text = DateTimeHelper.convertTimestampToReadableTime(detail.time)

            if (detail.comments != null) {
                tv_empty_comment.hide()
                layout_comment_list.show()
                detail.comments.map {
                    adapter.add(CommentItem(Comment("Ini komentar")))
                }
            } else {
                tv_empty_comment.show()
                layout_comment_list.hide()
            }
        }
    }
}
