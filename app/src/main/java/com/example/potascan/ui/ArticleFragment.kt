package com.example.potascan.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potascan.ViewModel.ArticleViewModel
import com.example.potascan.databinding.FragmentArticleBinding
import com.example.potascan.ViewModel.ViewModelFactoryArticle
import com.example.potascan.adapter.ArticleAdapter
import com.example.potascan.data.remote.article.DataItem
import com.example.potascan.databinding.ItemListArticleBinding


class ArticleFragment : Fragment() {
    val context =requireContext()
    private val viewModel by viewModels<ArticleViewModel> {
        ViewModelFactoryArticle.getInstance(context.dataStore)
    }
    private lateinit var binding: FragmentArticleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root

        binding.rvArticle.layoutManager = LinearLayoutManager(this)
        binding.rvArticle.setHasFixedSize(true)
        viewModel.listUser.observe(this, { listUser ->
            setUserData(listUser)
        })
    }

    private fun setArticle() {
        val adapter = ArticleAdapter(listArticle =  as ArrayList<ItemsItem>)

        viewModel.getUser().observe(this) {
            Log.d("LogoutTest", it.isLogin.toString())
            if (it.token != null) {
                viewModel.getAllStories().observe(this) { pagingData ->
                    Log.d("StoriesTest", "test")
                    adapter.submitData(lifecycle, pagingData)
                }
            }
        }
        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                Log.d("TestStory", data.toString())
                showSelectedStory(data)
            }
        })
    }

    private fun showSelectedStory(data: DataItem) {
        val detailIntent = Intent(context, DetailArticle::class.java)
        detailIntent.putExtra("name", data)
        startActivity(detailIntent)
    }

}