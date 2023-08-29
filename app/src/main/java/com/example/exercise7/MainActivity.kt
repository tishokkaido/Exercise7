package com.example.exercise7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise7.dto.QiitaDto
import com.example.exercise7.repository.QiitaPostRepository

class MainActivity : AppCompatActivity(), QiitaPostRepository.QiitaApiCallback {
    private lateinit var adapter: QiitaPostRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = QiitaPostRecyclerAdapter(emptyList())
        recyclerView = findViewById(R.id.qiita_post_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        errorTextView = findViewById(R.id.error_text_view)

        val repository = QiitaPostRepository()
        repository.fetchQiitaPost(this)
    }

    override fun onSuccess(result: List<QiitaDto>) {
        recyclerView.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE
        adapter.changeList(result)
    }

    override fun onFailure(t: Throwable) {
        recyclerView.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
    }
}