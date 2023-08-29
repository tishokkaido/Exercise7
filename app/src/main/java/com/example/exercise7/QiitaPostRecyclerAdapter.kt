package com.example.exercise7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise7.dto.QiitaDto

class QiitaPostRecyclerAdapter(qiitaPostList: List<QiitaDto>) : RecyclerView.Adapter<QiitaPostRecyclerAdapter.QiitaPostViewHolder>() {
    private var qiitaPosts = qiitaPostList.map { QiitaPost(it.title, it.tags[0].name) }

    override fun getItemCount(): Int = qiitaPosts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaPostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.qiita_post_list_item, parent, false)
        return QiitaPostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QiitaPostViewHolder, position: Int) {
        holder.bind(qiitaPosts[position])
    }

    fun changeList(qiitaPostList: List<QiitaDto>) {
        qiitaPosts = qiitaPostList.map { QiitaPost(it.title, it.tags[0].name) }
        this.notifyDataSetChanged()
    }

    class QiitaPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val view = itemView

        fun bind(qiitaPost: QiitaPost) {
            view.rootView.findViewById<TextView>(R.id.title_text_view).text = qiitaPost.title
            view.rootView.findViewById<TextView>(R.id.name_text_view).text = qiitaPost.name
        }
    }

    data class QiitaPost(
        val title: String,
        val name: String
    )
}