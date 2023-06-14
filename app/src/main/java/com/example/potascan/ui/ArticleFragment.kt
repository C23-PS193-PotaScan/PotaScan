package com.example.potascan.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potascan.ViewModel.ArticleViewModel
import com.example.potascan.ViewModel.ViewModelFactoryArticle
import com.example.potascan.adapter.ArticleAdapter
import com.example.potascan.data.Result
import com.example.potascan.data.remote.article.DataItem
import com.example.potascan.databinding.FragmentArticleBinding

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ArticleFragment : Fragment() {
    private val articleViewModel by viewModels<ArticleViewModel> {
        ViewModelFactoryArticle.getInstance(requireContext().dataStore)
    }
    private val listArticles: List<DataItem> = ArrayList()

    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        setStory()
        binding.rvArticle.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticle.setHasFixedSize(true)

        return binding.root

    }

    private fun setStory() {
        articleViewModel.getUser().observe(viewLifecycleOwner) {
            if (it.token != null) {

                articleViewModel.getAllStories(it.token).observe(viewLifecycleOwner) {result->
                    when(result){
                        is Result.Success ->{
                            val adapter = ArticleAdapter(result.data.listArticleData)
                            binding.rvArticle.adapter = adapter

                            adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: DataItem) {
                                    showSelectedStory(data)
                                }
                            })
                        }
                        is  Result.Error ->{

                        }else->{
                        Toast.makeText(requireContext(), "Data ditemukan", Toast.LENGTH_SHORT).show()
                    }

                }

                }
            }
        }


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