package com.example.potascan.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.potascan.data.remote.article.DataItem
import com.example.potascan.databinding.ActivityDetailArticleBinding


class DetailArticle : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    companion object {
        const val id = "id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra("title")
        val img = intent.getStringExtra("urlImg")
        val description = intent.getStringExtra("description")
        val writer = intent.getStringExtra("writer")
        val category = intent.getStringExtra("category")
        Log.d("onCreate: ", title.toString())
        Glide.with(this@DetailArticle)
            .load(img)
            .centerCrop()
            .into(binding.imgDetailArticle)
        binding.titleDetailArticle.text = title
        binding.descriptionDetailArticle.text = description
        binding.writerArticle.text = writer
        binding.categoryArticle.text = category

//        val article = if (Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra<DataItem>(id, DataItem::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra<DataItem>(id)
//        }
//        Log.d("Story", article.toString())
//
//        if (article != null) {
//            Glide.with(this@DetailArticle)
//                .load(article.imageUrlArticle)
//                .centerCrop()
//                .into(binding.imgDetailArticle)
//            binding.titleDetailArticle.text = article.title
//            binding.descriptionDetailArticle.text = article.mainContent
//            binding.writerArticle.text = article.writer
//            binding.categoryArticle.text = article.category
//        }
    }
}