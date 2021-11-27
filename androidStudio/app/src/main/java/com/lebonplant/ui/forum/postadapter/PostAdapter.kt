package com.lebonplant.ui.forum.postadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.lebonplant.R
import com.lebonplant.ui.forum.post.Post

class PostAdapter(private val context: Context,
                  private val dataSource: ArrayList<Post>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.post_list_item, parent, false);
        val subjectView = rowView.findViewById<View>(R.id.post_item_subject) as TextView
        val bodyView = rowView.findViewById<View>(R.id.post_item_body) as TextView
        val avatarView = rowView.findViewById<View>(R.id.post_item_avatar) as ImageView
        subjectView.setText(dataSource[position].header, TextView.BufferType.NORMAL)
        bodyView.setText(dataSource[position].body, TextView.BufferType.NORMAL)
        //avatarView.setImageURI("res/drawable/ic_notifications_black_24dp.xml")
        return rowView
    }
}