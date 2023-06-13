package com.example.potascan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.potascan.R


class DetailArticle : AppCompatActivity() {

    companion object {
        const val TITLE = "title"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)
    }
}