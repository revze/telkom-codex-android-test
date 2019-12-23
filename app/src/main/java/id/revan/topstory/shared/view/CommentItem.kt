package id.revan.topstory.shared.view

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import id.revan.topstory.R
import id.revan.topstory.data.model.Comment
import kotlinx.android.synthetic.main.item_row_comment.view.*

class CommentItem(private val text: String) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_comment.text = text
    }

    override fun getLayout() = R.layout.item_row_comment
}