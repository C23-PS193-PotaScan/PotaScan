package com.example.potascan.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potascan.ViewModel.ArticleViewModel
import com.example.potascan.ViewModel.ViewModelFactoryArticle
import com.example.potascan.adapter.ArticleAdapter
import com.example.potascan.data.remote.article.DataItem
import com.example.potascan.databinding.FragmentArticleBinding

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ArticleFragment : Fragment() {
    private val articleViewModel by viewModels<ArticleViewModel> {
        ViewModelFactoryArticle.getInstance(requireContext().dataStore)
    }

    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
        articleViewModel.getUser().observe(this){
            Log.d("LogoutTest", it.isLogin.toString())
            if(it.token != null){
                articleViewModel.getAllStories(it.token, "https://storage.googleapis.com/ngetest6/foto%20artikel/1.jpg", "Kentang adalah salah satu sumber karbohidrat yang cukup digemari sebagai pengganti nasi. Jenis umbi-umbian ini mudah diolah menjadi menu pembuka, utama, maupun penutup yang tentu saja lezat.", "Healty")
                articleViewModel.listArticle.observe(this, { listArticle ->
                    Log.d("TestDetailStory", listArticle.toString())
                    setStory(listArticle)
                })
            }


        }
    }

    private fun setStory(listArticle: List<DataItem>) {
        val adapter = ArticleAdapter(listArticle as ArrayList<DataItem>)
        binding.rvArticle.adapter=adapter
        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
//                Log.d("TestStory", data.toString())
                showSelectedStory(data)
            }
        })
    }

    private fun showSelectedStory(storyItem: DataItem) {
        val detailIntent = Intent(requireContext(), DetailArticle::class.java)
        detailIntent.putExtra("title", storyItem.title)
        detailIntent.putExtra("content", storyItem.mainContent)
        detailIntent.putExtra("category", storyItem.category)
        detailIntent.putExtra("image", storyItem.imageUrlArticle)
        startActivity(detailIntent)
    }

}