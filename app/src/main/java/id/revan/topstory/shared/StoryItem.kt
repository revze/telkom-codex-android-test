package id.revan.topstory.shared

import android.content.Intent
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import id.revan.topstory.R
import id.revan.topstory.ui.storydetail.StoryDetailActivity

class StoryItem(private val id: Int) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        viewHolder.itemView.setOnClickListener {
            Intent(context, StoryDetailActivity::class.java)
                .apply {
                    putExtra(StoryDetailActivity.ID, id)
                }.also { context.startActivity(it) }

        }
    }

    override fun getLayout() = R.layout.item_col_story
}