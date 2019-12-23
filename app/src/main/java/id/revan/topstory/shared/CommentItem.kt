package id.revan.topstory.shared

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import id.revan.topstory.R
import id.revan.topstory.data.model.Comment
import kotlinx.android.synthetic.main.item_row_comment.view.*

class CommentItem(private val comment: Comment) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_comment.text = comment.text
    }

    override fun getLayout() = R.layout.item_row_comment
}